import java.util.HashMap;

public class AnalysedDataProperties {
	private int numberOfPosts;
	private String targetHashtag;
	private float avgLikes;
	private float avgHashtags;
	private float avgWords;
	private float avgCharacters;
	private HashMap<String, Integer> relatedHashtags;
	
	public AnalysedDataProperties(int numberOfPosts, String targetHashtag, float avgLikes, float avgHashtags
			, float avgWords, float avgCharacters, HashMap<String, Integer> relatedHashtags) {
		this.numberOfPosts = numberOfPosts;
		this.targetHashtag = targetHashtag;
		this.avgLikes = avgLikes;
		this.avgHashtags = avgHashtags;
		this.avgWords = avgWords;
		this.avgCharacters = avgCharacters;
		this.relatedHashtags = relatedHashtags;
	}
	
	public int getNumberOfPosts() {
		return numberOfPosts;
	}
	
	public String getTargetHashtag() {
		return targetHashtag;
	}
	
	public float getAvgLikes() {
		return avgLikes;
	}
	
	public float getAvgHashtags() {
		return avgHashtags;
	}
	
	public float getAvgWords() {
		return avgWords;
	}
	
	public float getAvgCharacters() {
		return avgCharacters;
	}
	
	public HashMap<String, Integer> getRelatedHashtags(){
		return relatedHashtags;
	}
}
