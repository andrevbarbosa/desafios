package br.com.phzero.crawler;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class RedditCrawler {
	private static final String REDDIT_URL = "https://www.reddit.com/r/";
	private static final String TOP = "/top/";
	private static final Integer MIN_TOP = 5000;
	public static final Integer MAX_RETURN = 5;
	
	
	public static void main(String[] args) {
		RedditCrawler r = new RedditCrawler();
		try {
			r.doIt("news");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	public ArrayList<RedditCrawlerDTO> doIt(String subreddits) throws Exception {
		ArrayList<RedditCrawlerDTO> listReddits = new ArrayList<RedditCrawlerDTO>();
		String[] channel = subreddits.split(";");
		
		for (int c = 0; c<channel.length; c++) {
			String current = channel[c];
			String url = REDDIT_URL + current + TOP;
			
			Document document = Jsoup.connect(url).get();
			Elements things = document.select(".thing");
			
			int count = 0; 
			for (Element thing : things) {
				if (count>=MAX_RETURN) break;
				
				Integer score = (thing.attr("data-score")!=null? new Integer(thing.attr("data-score").trim()): -1);
				if (score < MIN_TOP) break;
				count++;
				
				RedditCrawlerDTO dto = new RedditCrawlerDTO();
				dto.setTitle(thing.select(".title .may-blank").text());
				dto.setScore(thing.attr("data-score"));
				dto.setUrl(url);
				dto.setLinkComments("https://www.reddit.com" + thing.attr("data-permalink"));
				dto.setLink(thing.attr("data-url"));
				
				listReddits.add(dto);
				
				System.out.println(thing.select(".title .may-blank").text());  
				System.out.println("Score: " + thing.attr("data-score"));
                System.out.println(url);
                System.out.println("Comments: https://www.reddit.com" + thing.attr("data-permalink"));
                System.out.println("Link: " + thing.attr("data-url"));
                System.out.println("-------");
                  
            }
		}
		
		return listReddits;
	}

}




















