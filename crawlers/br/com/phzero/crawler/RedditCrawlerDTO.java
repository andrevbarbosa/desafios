package br.com.phzero.crawler;

public class RedditCrawlerDTO {
	private String title;
	private String score;
	private String url;
	private String linkComments;
	private String link;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLinkComments() {
		return linkComments;
	}
	public void setLinkComments(String linkComments) {
		this.linkComments = linkComments;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public String toHtml() {
		StringBuilder h = new StringBuilder();
		
		h.append(this.url).append("\n")
		.append("<strong>").append("(").append(this.score).append(" points)\n")
		.append(this.title).append("</strong>\n")
		.append(this.linkComments).append("\n");
		
		return h.toString();
	}
}
