package socialposts;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class InstagramPost extends SocialMediaPost{
	
	private HashMap<Integer, Integer> reactions;
	
	public InstagramPost(String caption, String postedBy, Date postDate, ArrayList<String> replies, HashMap<Integer, Integer> reactions) {
		super(caption, postedBy, postDate, replies);
		this.reactions = reactions;
	}
	
	/* Getters and Setters */
	public HashMap<Integer, Integer> getReactionsList(){
		return reactions;
	}
}
