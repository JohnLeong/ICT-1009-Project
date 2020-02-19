package com.ict1009.stanfordcorenlp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TwitterSentimentAnalyzer extends SentimentAnalyzer {

	public TwitterSentimentAnalyzer() {
		super();
	}

	public TwitterSentimentAnalyzer(String modes) {
		super(modes);
	}
	
	/**
	 * Parses the JSON information scraped by TwitterScraper which contains all 
	 * details scraped for a particular HashTag
	 * @param jsonObject	JSONObject containing all details which is scraped by TwitterScraper
	 * @return				ArrayList of comments
	 */
	@Override
	protected ArrayList<String> parseJSONComments(JSONObject jsonObject) {
		ArrayList<String> comments = new ArrayList<String>();
		
		JSONArray posts = jsonObject.getJSONArray("extracted_posts");
		JSONArray postComments = new JSONArray();
	
		for (int i = 0; i < posts.length(); ++i) {
			/* Use caption as a form of comments too since twitter posts has very little comments */
			comments.add(posts.getJSONObject(i).getString("caption"));
			postComments = posts.getJSONObject(i).getJSONArray("comments");
			for (int j = 0; j < postComments.length(); ++j) {
				comments.add(postComments.getJSONObject(j).getString("desc"));
			}
		}
		return comments;		
	}
	
	/**
	 * Takes in a JSON file path which was previously scrapped by Twitter Scraper,
	 * reads the file into a String and then into a JSONObject for comments extraction.
	 * Each comment will then be categorized accordingly using categorizeComment function 
	 * and appended into the count of their respective category stored in a HashMap.
	 * 
	 * @param jsonPath		Full file path of JSON file created by TwitterScraper
	 * @return				Returns reactions of all comments HashMap in the format <Sentiment Category, Count>
	 */
	public HashMap<String, Integer> getTwitterSentimentResults(String jsonPath) {
		try {
			JSONObject contents = new JSONObject(readJSONFileToString(jsonPath));
			List<String> allComments = this.parseJSONComments(contents);
			return super.getSentimentResults(allComments);
		} catch (JSONException | IOException e1) {
			System.out.println("Error parsing JSON file.");
		}
		return null;
	}	

}
