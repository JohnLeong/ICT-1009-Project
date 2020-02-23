package com.ict1009.socialposts;

import java.util.ArrayList;
import java.util.Date;

public abstract class SocialMediaPost {
	
	protected String caption;
	protected String postedBy;
	protected Date postDate;
	protected ArrayList<String> replies;
	
	public SocialMediaPost(String caption, String postedBy, Date postDate, ArrayList<String> replies) {
		this.caption = caption;
		this.postedBy = postedBy;
		this.postDate = postDate;
		this.replies = replies;
	}
	
	/* Getters and Setters */
	public String getText() {
		return caption;
	}
	
	public String getPostedBy() {
		return postedBy;
	}
	
	public Date getPostDate() {
		return postDate;
	}
	
	public ArrayList<String> getReplies() {
		return replies;
	}
}
