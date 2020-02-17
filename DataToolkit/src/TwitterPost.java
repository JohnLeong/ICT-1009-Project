import java.util.ArrayList;

public class TwitterPost extends SocialMediaPost{
	
	private ArrayList<String> hashTagList;
	
	public TwitterPost(String text) {
		super(text);
		hashTagList = new ArrayList<String>();
	}
	
	public TwitterPost(String text, ArrayList<String> hashTagList) {
		super(text);
		this.hashTagList = hashTagList;
	}
	
	
	/* Getters and Setters */
	public ArrayList<String> getHashTagList(){
		return hashTagList;
	}
}
