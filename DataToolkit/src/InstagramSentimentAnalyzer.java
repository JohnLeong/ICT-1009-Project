import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	private ArrayList<String> getCommentsFromAllPosts(JSONObject jsonObject) {
		ArrayList<String> comments = new ArrayList<String>();
		JSONArray posts = jsonObject.getJSONArray("extracted_posts");
		JSONArray postComments = new JSONArray();
		
		for (int i = 0; i < posts.length(); ++i) {
			postComments = posts.getJSONObject(i).getJSONArray("comments");
			for (int j = 0; j < postComments.length(); ++j) {
				comments.add(postComments.getJSONObject(j).getString("desc"));
			}
		}
		return comments;
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
	public HashMap<String, Integer> getInstagramSentimentResults(String jsonPath) {
		HashMap<String, Integer> reactions = new HashMap<String, Integer>();
		String fileString; JSONObject contents;
		
		try {
			fileString = new String(Files.readAllBytes(Paths.get(jsonPath)));
			contents = new JSONObject(fileString);
			
			List<String> allComments = getCommentsFromAllPosts(contents);
			int mapKeyCount; String reaction;
			for (String comment : allComments) {
				reaction = super.commentCategory(comment);
				System.out.println(reaction);
				mapKeyCount = reactions.containsKey(reaction) ? reactions.get(reaction) : 0;
				reactions.put(reaction, mapKeyCount + 1);
			}
			
		} catch (JSONException e1) {
			System.out.println("Error parsing JSON.");
		} catch (IOException e) {
			System.out.println(e);
		}	
		
		return reactions;
	}	
}
