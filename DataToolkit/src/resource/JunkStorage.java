package resource;

import java.io.IOException;
import java.util.LinkedHashSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
/**
 * 
 * For storing codes which tried to use previously but not used in the end.
 * Delete at the end when really not planning to use...
 */
 
public class JunkStorage {
	
	private final String STRIP_JSON_OUTPUT 		= "<script type=\"text/javascript\">window._sharedData = ";
	private final String STRIP_JSON_OUTPUT2	 	= ";</script>";
	
	public void parsePostJSON(String url) {
		//The basenode will be used to get all info later on...
		JSONObject baseNode = new JSONObject(getJsonStringFromPage(url))
				.getJSONObject("entry_data")
				.getJSONArray("PostPage")
				.getJSONObject(0)
				.getJSONObject("graphql")
				.getJSONObject("shortcode_media");
		System.out.println(baseNode);
		//[MainPost NoOfLikes] base -> edge_media_preview_like(object) -> count.getInt
		long numberofLikes = baseNode.getJSONObject("edge_media_preview_like").getLong("count");
		System.out.println("NumberOfLikes: " + numberofLikes);

		//[MainPost Caption] base -> edge_media_to_caption -> edges -> node -> text
		String caption = baseNode.getJSONObject("edge_media_to_caption")
				.getJSONArray("edges")
				.getJSONObject(0)
				.getJSONObject("node")
				.getString("text");
		System.out.println("caption: " + caption);

		//[Location of Post] base -> location.getSmth.. //If null will be Xxxx
		//		JSONObject location = baseNode.getJSONObject("location");
		//		System.out.println(location);

		//[Is Video] base -> is_video.getBoolean?
		boolean postIsVideo = baseNode.getBoolean("is_video");
		System.out.println(postIsVideo);

		//[TimeStamp(In epoch form)] base -> taken_at_timestamp
		//[Comment Total Comments Count] base -> edge_media_to_parent_comment(obj) -> count.getInt?
		long commentsCount = baseNode.getJSONObject("edge_media_to_parent_comment").getLong("count");
		System.out.println("Total Number of Comments: " + commentsCount);
		//[Comments text] base -> edge_media_to_parent_comment(obj) -> edges.getArray? -> foreach get node
		JSONArray postNodes = baseNode.getJSONObject("edge_media_to_parent_comment").getJSONArray("edges");
		System.out.println("ArrayPost (Edges): " + postNodes);

	}
	
	private void extractAllPosts(LinkedHashSet<String> postSubUrls) throws IOException {
		Document doc;
		for (String subUrl : postSubUrls) {
			doc = Jsoup.connect("https://www.instagram.com" + subUrl).get();
		}
	}
	
	private String getJsonStringFromPage(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			for (Element e : doc.select("script")) {
				if (e.toString().contains(STRIP_JSON_OUTPUT)) {					
					return e.toString()
							.replace(STRIP_JSON_OUTPUT, "")
							.replace(STRIP_JSON_OUTPUT2, "");
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();

		}
		return "";		
	}
	
}
