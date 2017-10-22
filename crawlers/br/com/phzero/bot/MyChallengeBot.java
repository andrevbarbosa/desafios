package br.com.phzero.bot;

import java.util.ArrayList;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import br.com.phzero.crawler.RedditCrawler;
import br.com.phzero.crawler.RedditCrawlerDTO;

public class MyChallengeBot {
	private static final String UNIQUE_VALID_COMMAND = "/nadaprafazer ";
	
	public static void main(String[] args) {
		MyChallengeBot m = new MyChallengeBot();
		m.runBot();
	}

	public void runBot() {
		RedditCrawler crawler = new RedditCrawler();
		Integer offSet = 0;
		
		System.out.println("creating bot...");
		TelegramBot bot = new TelegramBot("471079914:AAHggZ9hLUY1SYn_vKOOAde6u8oukbI18Dk");
		
		while (true) {
			//System.out.println("creating getUpdates...");
			GetUpdates getUpdates = new GetUpdates().limit(5).offset(offSet).timeout(0);

			//System.out.println("executing getUpdates...");
			GetUpdatesResponse updatesResponse = bot.execute(getUpdates);

			//System.out.println("listing updates...");
			List<Update> updates = updatesResponse.updates();

			for (Update u: updates) {

				System.out.println("text = " + u.message().text() + " - updateId = " + u.updateId()
				+ " - chatId = " + u.message().chat().id());

				offSet = u.updateId() + 1;
				Long chatId = u.message().chat().id();
				String command = u.message().text().toLowerCase();

				if (!command.startsWith(UNIQUE_VALID_COMMAND)) {
					sendMessageToClient(bot, chatId, 
							"Unrecognized command!!!!\n Syntax: \"/NadaPraFazer [list of reddits separated by \";\"]. "
									+ "\nEx: /NadaPraFazer gaming;cats;television");
					continue;
				}

				String subreddits = command.substring(command.trim().indexOf(" ")).trim();
				
				if (subreddits.indexOf(" ") > -1) {
					sendMessageToClient(bot, chatId, 
							"Unrecognized command!!!!\n Syntax: \"/NadaPraFazer [list of reddits separated by \";\"]. "
									+ "\nEx: /NadaPraFazer gaming;cats;television");

					continue;					
				}
				
				try {
					ArrayList<RedditCrawlerDTO> list = crawler.doIt(subreddits);

					if (list.size()==0) {
						sendMessageToClient(bot, chatId, "Nothing hot about " + subreddits);
					}

					for(RedditCrawlerDTO r: list) {
						sendMessageToClient(bot, chatId, r.toHtml());
					}
				} catch (Exception e) {
					e.printStackTrace();
					
					sendMessageToClient(bot, chatId, 
							"Unrecognized command!!!! Syntax is \"/NadaPraFazer [list of reddits separated by \";\"]. "
							+ "Ex: /NadaPraFazer gaming;cats;television");
				}

				System.out.println("text = " + u.message().text() + " - updateId = " + u.updateId()
				+ " - chatId = " + u.message().chat().id());
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendMessageToClient(TelegramBot bot, Long chatId, String text) {
		SendMessage request = new SendMessage(chatId, text)
				.parseMode(ParseMode.HTML)
				.disableWebPagePreview(true)
				.disableNotification(true);

		bot.execute(request);
	}
	

}
