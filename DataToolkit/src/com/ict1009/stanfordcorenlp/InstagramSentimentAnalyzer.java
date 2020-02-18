package com.ict1009.stanfordcorenlp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InstagramSentimentAnalyzer extends SentimentAnalyzer{
	

	public InstagramSentimentAnalyzer() {
		super();
	}

	public InstagramSentimentAnalyzer(String modes) {
		super(modes);
	}
	
	/**
	 * Parses the JSON information scraped by InstagramScraper which contains all 
	 * details scraped for a particular HashTag
	 * @param jsonObject	JSONObject containing all details which is scraped by InstagramScraper
	 * @return				ArrayList of comments
	 */
	@Override
	protected ArrayList<String> parseJSONComments(JSONObject jsonObject) {
		ArrayList<String> comments = new ArrayList<String>();
		JSONArray details = jsonObject.getJSONArray("details");
		for (int i = 0; i < details.length(); ++i) {
			JSONArray posts = ((JSONObject)details.get(i)).getJSONArray("extracted_posts");
			JSONArray postComments = new JSONArray();
			for (int j = 0; j < posts.length(); ++j) {
				postComments = posts.getJSONObject(j).getJSONArray("comments");
				for (int k = 0; k < postComments.length(); ++k) {
					comments.add(postComments.getJSONObject(k).getString("desc"));
				}
			}
		}
		return comments;
	}
	
	protected ArrayList<String> parseJsonOcrText(JSONObject jsonObject) {
		ArrayList<String> ocrSentences = new ArrayList<String>();
		JSONArray details = jsonObject.getJSONArray("details");
		for (int i = 0; i < details.length(); ++i) {
			JSONArray posts = ((JSONObject)details.get(i)).getJSONArray("extracted_posts");
//			JSONArray posts = jsonObject.getJSONArray("extracted_posts");
			JSONArray postComments = new JSONArray();
			for (int j = 0; j < posts.length(); ++j) {
				ocrSentences.add(posts.getJSONObject(j).getString("img_ocr_text"));
			}
		}
		return ocrSentences;
	}
	
	/**
	 * Takes in a JSON file path which was previously scrapped by Instagram Scraper,
	 * reads the file into a String and then into a JSONObject for comments extraction.
	 * Each comment will then be categorized accordingly using categorizeComment function 
	 * and appended into the count of their respective category stored in a HashMap.
	 * 
	 * @param jsonPath		Full file path of JSON file created by InstagramScraper
	 * @return				Returns reactions of all comments HashMap in the format <Sentiment Category, Count>
	 */
	public HashMap<String, Integer> getInstagramSentimentResults(String jsonPath, boolean parseOcr) {
		try {
			JSONObject contents = new JSONObject(readJSONFileToString(jsonPath));
			List<String> toAnalyse = new ArrayList<String>();
//			System.out.println(contents.getString("scrape_mode") + "    " + contents.getString("platform"));
			if (!contents.getString("scrape_mode").equals("profiles") && !contents.getString("platform").equals("instagram")) {
				toAnalyse.addAll(this.parseJSONComments(contents));
			}
			if (parseOcr) { toAnalyse.addAll(this.parseJsonOcrText(contents)); }
			return super.getSentimentResults(toAnalyse);
		} catch (JSONException | IOException e1) {
			e1.printStackTrace();
			System.out.println("Error parsing JSON file.");
		}
		return null;
	}	
}