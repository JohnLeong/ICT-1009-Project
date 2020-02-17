import java.awt.Color;
import java.awt.Dimension;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kennycason.kumo.*;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

public class DataAnalyser {
	
	/**
	 * Reads a json file at the specifieed file path and analyses it
	 * 
	 * @param filePath		The file to analyse
	 * @return				The propeties of the analysed data
	 */
	public static AnalysedDataProperties AnalyseData(String filePath) throws Exception {
		int numberOfPosts = 0, totalLikes = 0, totalWords = 0, totalCharacters = 0, totalHashtags = 0;
		String targetHashtag = "";
		HashMap<String, Integer> relatedHashtags = new HashMap<String, Integer>();
		JSONObject data;
		ArrayList<String> wordMap = new ArrayList<String>();
		
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
				for(String word : words)
					wordMap.add(word);
				totalWords += words.length;
				totalCharacters += caption.length();
				
				//Find all hashtags in caption
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
				
				if (post.has("img_ocr_text")){
					String[] ocr_words = post.getString("img_ocr_text").split("\\s+");
					for(String word : ocr_words)
						wordMap.add(word);
				}
			}
		}
		
		final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
		final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(wordMap);
		final Dimension dimension = new Dimension(600, 600);
		final WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
		wordCloud.setPadding(2);
		wordCloud.setBackground(new CircleBackground(300));
		wordCloud.setBackgroundColor(new Color(0xb3b3b3));
		wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
		wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
		wordCloud.build(wordFrequencies);
		wordCloud.writeToFile( Paths.get("").toAbsolutePath().toString() + "/wordmap.png");
		
		float averageLikes = Math.round(((float)totalLikes / numberOfPosts) * 100.0f) / 100.0f;
		float averageHashtags = Math.round(((float)totalHashtags / numberOfPosts) * 100.0f) / 100.0f;
		float averageWords = Math.round(((float)totalWords / numberOfPosts) * 100.0f) / 100.0f;
		float averageChars = Math.round(((float)totalCharacters / numberOfPosts) * 100.0f) / 100.0f;
					
		return (new AnalysedDataProperties(numberOfPosts, targetHashtag, averageLikes
				, averageHashtags, averageWords
				, averageChars, data.getString("scrape_mode"), relatedHashtags));
	}
}
