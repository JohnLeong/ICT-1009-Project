import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataAnalyser {
	public static AnalysedDataProperties AnalyseData(String filePath) throws Exception {
		int numberOfPosts, totalLikes = 0, totalWords = 0, totalCharacters = 0, totalHashtags = 0;
		String targetHashtag;
		HashMap<String, Integer> relatedHashtags = new HashMap<String, Integer>();
		JSONObject data;
		
		try {
			data = new JSONObject(JSONUtility.parseJSONToString(filePath)) ;
		}
		catch (Exception e) {
			throw new Exception("Unable to read file");
		}
		
		JSONArray extractedPosts =  data.getJSONArray("extracted_posts");
		numberOfPosts = extractedPosts.length();
		targetHashtag = data.getString("hash_tag");
		
		//Loop through all posts
		for(int i = 0; i < numberOfPosts; ++i) {
			//Get current post
			JSONObject post = extractedPosts.getJSONObject(i);
			
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
				
		return (new AnalysedDataProperties(numberOfPosts, targetHashtag, (float)totalLikes / numberOfPosts
				, (float)totalHashtags / numberOfPosts, (float)totalWords / numberOfPosts
				, (float)totalCharacters / numberOfPosts, relatedHashtags));
	}
}
