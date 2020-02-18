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
