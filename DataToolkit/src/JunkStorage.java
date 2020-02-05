

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
	
	
	private final String JS_NUM_OF_LIKES = "/entry_data/PostPage/0/graphql/shortcode_media/edge_media_preview_like/count";
	private final String JS_IS_VIDEO = "/entry_data/PostPage/0/graphql/shortcode_media/is_video";	
	private final String JS_NUM_OF_VIDEO_VIEWS = "/entry_data/PostPage/0/graphql/shortcode_media/video_view_count/";	
	private final String JS_NUM_OF_COMMENTS = "/entry_data/PostPage/0/graphql/shortcode_media/edge_media_to_parent_comment/count";
	private final String JS_VIDEO_URL 		= "/entry_data/PostPage/0/graphql/shortcode_media/video_url";
	
	public Object returnQueryObject(final String jsonString, final String query) {
		return (new JSONObject(jsonString)).optQuery(query);
	}
	
	public long getNumberOfComments(String jsonString) { 
		return returnQueryObject(jsonString, JS_NUM_OF_COMMENTS) != null ?
				((Number)returnQueryObject(jsonString, JS_NUM_OF_COMMENTS)).longValue() :
					-1;
	}
	public long getNumberOfLikes(String jsonString) {
		return returnQueryObject(jsonString, JS_NUM_OF_LIKES) != null ?
				((Number)returnQueryObject(jsonString, JS_NUM_OF_LIKES)).longValue() : 
				-1;
	}
	public long getNumberOfVideoViews(String jsonString) {
		
		return returnQueryObject(jsonString, JS_NUM_OF_VIDEO_VIEWS) != null ? 
				((Number)returnQueryObject(jsonString, JS_NUM_OF_VIDEO_VIEWS)).longValue() : 
					-1;
	}
	
	public boolean getIsVideo(String jsonString) {
		return ((Boolean)returnQueryObject(jsonString, JS_IS_VIDEO)).booleanValue();
	}
	
	public String getVideoUrl(String jsonString) {
		return returnQueryObject(jsonString, JS_VIDEO_URL) != null ? 
				returnQueryObject(jsonString, JS_VIDEO_URL).toString() : 
					"None";
	}
	//Objective is to retrieve number of likes and total number of comments for each url. 
	
//	public int getNumberOfLikes(String jsonString) {
//		JSONObject baseNode = new JSONObject(jsonString)
//		
//		return 1;
//		
//	}
	
	public void dryScrapeUrl(String url) {
		//The basenode will be used to get all info later on...
		JSONObject baseNode = new JSONObject(getWindowSharedDataJson(url))
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
	
	public String getWindowSharedDataJson(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
					.timeout(12000) 
					.followRedirects(true)
					.get();
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
