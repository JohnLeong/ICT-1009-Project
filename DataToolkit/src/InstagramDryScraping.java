import java.io.IOException;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public interface InstagramDryScraping {
	final String STRIP_JSON_OUTPUT = "<script type=\"text/javascript\">window._sharedData = ";
	final String STRIP_JSON_OUTPUT2	= ";</script>";

	final String SOUP_USER_AGENT			= "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
	
	final String JS_NUM_OF_LIKES 			= "/entry_data/PostPage/0/graphql/shortcode_media/edge_media_preview_like/count";
	final String JS_IS_VIDEO 				= "/entry_data/PostPage/0/graphql/shortcode_media/is_video";	
	final String JS_NUM_OF_VIDEO_VIEWS 		= "/entry_data/PostPage/0/graphql/shortcode_media/video_view_count/";	
	final String JS_NUM_OF_COMMENTS 		= "/entry_data/PostPage/0/graphql/shortcode_media/edge_media_to_parent_comment/count";
	final String JS_VIDEO_URL 				= "/entry_data/PostPage/0/graphql/shortcode_media/video_url";
	final String JS_IMAGE_DISPLAY_URL		="/entry_data/PostPage/0/graphql/shortcode_media/display_url";
	
	
	default Object returnQueryObject(final String postUrl, final String query) {
		return (new JSONObject(getWindowSharedDataJson(postUrl))).optQuery(query);
	}

	default long getNumberOfComments(final String posturl) { 
		return returnQueryObject(posturl, JS_NUM_OF_COMMENTS) != null ?
				((Number)returnQueryObject(posturl, JS_NUM_OF_COMMENTS)).longValue() :
					-1;
	}

	default long getNumberOfLikes(final String postUrl) {
		return returnQueryObject(postUrl, JS_NUM_OF_LIKES) != null ?
				((Number)returnQueryObject(postUrl, JS_NUM_OF_LIKES)).longValue() : 
					-1;
	}
	default long getNumberOfVideoViews(final String postUrl) {
		return returnQueryObject(postUrl, JS_NUM_OF_VIDEO_VIEWS) != null ? 
				((Number)returnQueryObject(postUrl, JS_NUM_OF_VIDEO_VIEWS)).longValue() : 
					-1;
	}

	default boolean getIsVideo(final String postUrl) {
		return ((Boolean)returnQueryObject(postUrl, JS_IS_VIDEO)).booleanValue();
	}

	default String getVideoUrl(final String postUrl) {
		return returnQueryObject(postUrl, JS_VIDEO_URL) != null ? 
				returnQueryObject(postUrl, JS_VIDEO_URL).toString() : 
					"None";
	}
	
	default String getDisplayImageUrl(final String postUrl) {
		return returnQueryObject(postUrl, JS_IMAGE_DISPLAY_URL) != null ?
				returnQueryObject(postUrl, JS_IMAGE_DISPLAY_URL).toString() :
					"None";
	}
	

	default String getWindowSharedDataJson(String postUrl) {
		Document doc;
		try {
			doc = Jsoup.connect(postUrl)
					.userAgent(SOUP_USER_AGENT)
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
