import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataAnalyser {
	public static AnalysedDataProperties AnalyseData(String filePath) throws Exception {
		int numberOfPosts = 0, totalLikes = 0, totalWords = 0, totalCharacters = 0, totalHashtags = 0;
		String targetHashtag = "";
		HashMap<String, Integer> relatedHashtags = new HashMap<String, Integer>();
		JSONObject data;
		
		try {
			data = new JSONObject(JSONUtility.parseJSONToString(filePath)) ;
		}
		catch (Exception e) {
			throw new Exception("Unable to read file");
		}
		
		JSONArray extractedHashTagsData = data.getJSONArray("details");
		
		for(int i = 0; i < extractedHashTagsData.length(); ++i) {
			JSONObject hashTagData = extractedHashTagsData.getJSONObject(i);
			JSONArray extractedPosts =  hashTagData.getJSONArray("extracted_posts");
			numberOfPosts += extractedPosts.length();
			if (hashTagData.has("hash_tag"))
				targetHashtag += hashTagData.getString("hash_tag") + " ";
			else
				targetHashtag += hashTagData.getString("profile_name") + " ";
			
			//Loop through all posts
			for(int j = 0; j < extractedPosts.length(); ++j) {
				//Get current post
				JSONObject post = extractedPosts.getJSONObject(j);
				
				//Add to total likes
				totalLikes += post.getInt("no_of_likes");
				
				//Get caption of current post
				String caption = post.getString("caption");
				
				//Add number of words/characters of current post to total
				String[] words = caption.split("\\s+");
				totalWords += words.length;
				totalCharacters += caption.length();
				
				//Find all hashtags in caption
				/*
				for(String word : words) {
					if (word.charAt(0) == '#') {
						++totalHashtags;
						if (relatedHashtags.containsKey(word))
							relatedHashtags.put(word, relatedHashtags.get(word) + 1);
						else
							relatedHashtags.put(word, 1);
					}
				}*/

				Pattern hashtagPattern = Pattern.compile("#(\\S+)");
				Matcher mat = hashtagPattern.matcher(caption);
				while (mat.find()) {
					++totalHashtags;
					String h = mat.group(1).toLowerCase();
					if (h.equals(targetHashtag.toLowerCase()))
						continue;
					if (relatedHashtags.containsKey(h))
						relatedHashtags.put(h, relatedHashtags.get(h) + 1);
					else
						relatedHashtags.put(h, 1);
				}
			}
		}
		
		float averageLikes = Math.round(((float)totalLikes / numberOfPosts) * 100.0f) / 100.0f;
		float averageHashtags = Math.round(((float)totalHashtags / numberOfPosts) * 100.0f) / 100.0f;
		float averageWords = Math.round(((float)totalWords / numberOfPosts) * 100.0f) / 100.0f;
		float averageChars = Math.round(((float)totalCharacters / numberOfPosts) * 100.0f) / 100.0f;
					
		return (new AnalysedDataProperties(numberOfPosts, targetHashtag, averageLikes
				, averageHashtags, averageWords
				, averageChars, data.getString("scrape_mode"), relatedHashtags));
	}
}
