package com.ict1009.socialposts;
import java.util.ArrayList;
import java.util.Date;

public class TwitterPost extends SocialMediaPost{
	
	public TwitterPost(String caption) {
		super(caption, "", null, null);
	}
	
	public TwitterPost(String caption, String postedBy, Date postDate, ArrayList<String> replies) {
		super(caption, postedBy, postDate, replies);
	}

	/* Getters and Setters */
	public ArrayList<String> getHashTagList(){
		return null;
	}
}
