package com.ict1009.analysis;
import java.io.OutputStream;
import java.util.HashMap;

public class AnalysedDataProperties {
	private int numberOfPosts;							//The total number of posts in the dataset
	private String targetHashtag;						//The target hashtag/profile string of the dataset
	private float avgLikes;								//The average number of likes in the dataset
	private float avgHashtags;							//The average number of hashtags per post in the dataset
	private float avgWords;								//The average number of words per post in the dataset
	private float avgCharacters;						//The average number of characters per post in the dataset
	private HashMap<String, Integer> relatedHashtags;	//The hashmap of hashtags related to the posts in the dataset
	private String scrapeType;							//Scraped by hashtag or profile
	private OutputStream oStream;						//The stream to output the generated wordmap
	
	public AnalysedDataProperties(int numberOfPosts, String targetHashtag, float avgLikes, float avgHashtags
			, float avgWords, float avgCharacters, String scrapeType, HashMap<String, Integer> relatedHashtags) {
		this.numberOfPosts = numberOfPosts;
		this.targetHashtag = targetHashtag;
		this.avgLikes = avgLikes;
		this.avgHashtags = avgHashtags;
		this.avgWords = avgWords;
		this.avgCharacters = avgCharacters;
		this.scrapeType = scrapeType;
		this.relatedHashtags = relatedHashtags;
	}
	
	public AnalysedDataProperties(int numberOfPosts, String targetHashtag, float avgLikes, float avgHashtags
			, float avgWords, float avgCharacters, String scrapeType, HashMap<String, Integer> relatedHashtags
			, OutputStream oStream) {
		this.numberOfPosts = numberOfPosts;
		this.targetHashtag = targetHashtag;
		this.avgLikes = avgLikes;
		this.avgHashtags = avgHashtags;
		this.avgWords = avgWords;
		this.avgCharacters = avgCharacters;
		this.scrapeType = scrapeType;
		this.relatedHashtags = relatedHashtags;
		this.oStream = oStream;
	}
	
	/**
	 * @return Returns the number of posts in the analysed dataset
	 */
	public int getNumberOfPosts() {
		return numberOfPosts;
	}
	
	/**
	 * @return Returns the target hashtag(s)/profile(s) in the analysed dataset
	 */
	public String getTargetHashtag() {
		return targetHashtag;
	}
	
	/**
	 * @return Returns the average likes per post in the analysed dataset
	 */
	public float getAvgLikes() {
		return avgLikes;
	}
	
	/**
	 * @return Returns the average number of hashtags per post in the analysed dataset
	 */
	public float getAvgHashtags() {
		return avgHashtags;
	}
	
	/**
	 * @return Returns the average number of words per post in the analysed dataset
	 */
	public float getAvgWords() {
		return avgWords;
	}
	
	/**
	 * @return Returns the average number of characters per post in the analysed dataset
	 */
	public float getAvgCharacters() {
		return avgCharacters;
	}
	
	/**
	 * @return Returns the related hashtags in the analysed dataset
	 */
	public HashMap<String, Integer> getRelatedHashtags(){
		return relatedHashtags;
	}
	
	/**
	 * @return Returns the scrape type(hashtag/profile) of the analysed dataset
	 */
	public String getScrapeType() {
		return scrapeType;
	}
	
	/**
	 * @return Returns the output stream for the generated wordmap
	 */
	public OutputStream getOStream() {
		return oStream;
	}
}
