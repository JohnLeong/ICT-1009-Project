package com.ict1009.scrapper;

import org.json.JSONObject;

import com.ict1009.utilities.TimeStampConverter;

public interface InstagramDryScraping {

	//Add datetime and location 
	/**
	 *  Constant strings to be used with JSONQuery to retrieve JSON fields  
	 */
	final String JS_NUM_OF_LIKES 			= "/entry_data/PostPage/0/graphql/shortcode_media/edge_media_preview_like/count";
	final String JS_IS_VIDEO 				= "/entry_data/PostPage/0/graphql/shortcode_media/is_video";	
	final String JS_NUM_OF_VIDEO_VIEWS 		= "/entry_data/PostPage/0/graphql/shortcode_media/video_view_count/";	
	final String JS_NUM_OF_COMMENTS 		= "/entry_data/PostPage/0/graphql/shortcode_media/edge_media_to_parent_comment/count";
	final String JS_VIDEO_URL 				= "/entry_data/PostPage/0/graphql/shortcode_media/video_url";
	final String JS_IMAGE_DISPLAY_URL		= "/entry_data/PostPage/0/graphql/shortcode_media/display_url";
	final String JS_CAPTION 				= "/entry_data/PostPage/0/graphql/shortcode_media/edge_media_to_caption/edges/0/node/text";
	final String JS_TIMESTAMP 				= "/entry_data/PostPage/0/graphql/shortcode_media/taken_at_timestamp"; // get as string
	final String JS_LOCATION 				= "/entry_data/PostPage/0/graphql/shortcode_media/location";			//get as string



	final String JS_ADD_NUM_OF_LIKES 			= "/graphql/shortcode_media/edge_media_preview_like/count";	
	final String JS_ADD_IS_VIDEO 				= "/graphql/shortcode_media/is_video";	
	final String JS_ADD_NUM_OF_VIDEO_VIEWS 		= "/graphql/shortcode_media/video_view_count/";	
	final String JS_ADD_NUM_OF_COMMENTS 		= "/graphql/shortcode_media/edge_media_to_parent_comment/count";	
	final String JS_ADD_VIDEO_URL 				= "/graphql/shortcode_media/video_url";	
	final String JS_ADD_IMAGE_DISPLAY_URL		= "/graphql/shortcode_media/display_url";
	final String JS_ADD_CAPTION 				= "/graphql/shortcode_media/edge_media_to_caption/edges/0/node/text";
	final String JS_ADD_TIMESTAMP 				= "/graphql/shortcode_media/taken_at_timestamp"; 		// get as string
	final String JS_ADD_LOCATION 				= "/graphql/shortcode_media/location";			//get as string

	default Object returnQueryWinData(final String postUrl, final String query) {
		return (new JSONObject(JsoupUtility.getWindowSharedDataJson(postUrl))).optQuery(query);
	}

	default Object returnQueryWinAdditionalData(final String postUrl, final String query) {
		return (new JSONObject(JsoupUtility.getAditonalWindowSharedDataJson(postUrl))).optQuery(query);
	}

	/**
	 * Gets the number of comments from the post at the specified URL
	 * 
	 * @param posturl	The URL of the post
	 * @return			The number of comments in the post
	 */
	default long getNumberOfComments(final String posturl) { 
		return returnQueryWinData(posturl, JS_NUM_OF_COMMENTS) != null ?
				((Number)returnQueryWinData(posturl, JS_NUM_OF_COMMENTS)).longValue() :
					returnQueryWinAdditionalData(posturl, JS_ADD_NUM_OF_COMMENTS) != null ?
							((Number)returnQueryWinAdditionalData(posturl, JS_ADD_NUM_OF_COMMENTS)).longValue() :
								-1;
	}


	/**
	 * Gets the number of likes from the post at the specified URL
	 * 
	 * @param postUrl	The URL of the post
	 * @return			The number of likes in the post
	 */
	default long getNumberOfLikes(final String postUrl) {
		return returnQueryWinData(postUrl, JS_NUM_OF_LIKES) != null ?
				((Number)returnQueryWinData(postUrl, JS_NUM_OF_LIKES)).longValue() : 
					returnQueryWinAdditionalData(postUrl, JS_ADD_NUM_OF_LIKES) != null ? 
							((Number)returnQueryWinAdditionalData(postUrl,JS_ADD_NUM_OF_LIKES)).longValue() :
								-1;
	}

	/**
	 * Gets the number of views from the video post at the specified URL
	 * 
	 * @param postUrl	The URL of the video post
	 * @return			The number of views of the video post
	 */
	default long getNumberOfVideoViews(final String postUrl) {
		return returnQueryWinData(postUrl, JS_NUM_OF_VIDEO_VIEWS) != null ? 
				((Number)returnQueryWinData(postUrl, JS_NUM_OF_VIDEO_VIEWS)).longValue() : 
					returnQueryWinAdditionalData(postUrl, JS_ADD_NUM_OF_VIDEO_VIEWS) != null ?
							((Number)returnQueryWinAdditionalData(postUrl, JS_ADD_NUM_OF_VIDEO_VIEWS)).longValue() :
								-1;
	}

	/**
	 * Checks if the post at the specified URL is a video
	 * 
	 * @param postUrl	The URL of the post to check
	 * @return			Returns true if the post is a video
	 */
	default boolean getIsVideo(final String postUrl) {
		System.out.println(postUrl);
		return returnQueryWinData(postUrl, JS_IS_VIDEO) != null ?
				((Boolean)returnQueryWinData(postUrl, JS_IS_VIDEO)).booleanValue() : 
					returnQueryWinAdditionalData(postUrl, JS_ADD_IS_VIDEO) != null ? 
							((Boolean)returnQueryWinAdditionalData(postUrl, JS_ADD_IS_VIDEO)).booleanValue() :
								false;

	}

	/**
	 * Gets the URL of the video from a post
	 * 
	 * @param postUrl	The URL of the post
	 * @return			The URL of the video
	 */
	default String getVideoUrl(final String postUrl) {
		return returnQueryWinData(postUrl, JS_VIDEO_URL) != null ? 
				returnQueryWinData(postUrl, JS_VIDEO_URL).toString() : 
					returnQueryWinAdditionalData(postUrl, JS_ADD_VIDEO_URL) != null ? 
							returnQueryWinAdditionalData(postUrl, JS_ADD_VIDEO_URL).toString() :
								"None";
	}

	/**
	 * Gets the URL of the image from a post
	 * 
	 * @param postUrl	The URL of the post
	 * @return			The URL of the image
	 */
	default String getDisplayImageUrl(final String postUrl) {
		return returnQueryWinData(postUrl, JS_IMAGE_DISPLAY_URL) != null ?
				returnQueryWinData(postUrl, JS_IMAGE_DISPLAY_URL).toString() :
					returnQueryWinAdditionalData(postUrl, JS_ADD_IMAGE_DISPLAY_URL) != null ? 
							returnQueryWinAdditionalData(postUrl, JS_ADD_IMAGE_DISPLAY_URL).toString() :
								"None";
	}

	/**
	 * Gets the caption of the post at the specified URL
	 * 
	 * @param postUrl	The URL of the post
	 * @return			The caption of the post
	 */
	default String getPostCaption(final String postUrl) {
		return returnQueryWinData(postUrl, JS_CAPTION) != null ?
				returnQueryWinData(postUrl, JS_CAPTION).toString() :
					returnQueryWinAdditionalData(postUrl, JS_ADD_CAPTION) != null ?
							returnQueryWinAdditionalData(postUrl, JS_ADD_CAPTION).toString() :
								"None";
	}

	default String getPostLocation(final String postUrl) {
		String location = returnQueryWinData(postUrl, JS_LOCATION) != null ?
				returnQueryWinData(postUrl, JS_LOCATION).toString() :
					returnQueryWinAdditionalData(postUrl, JS_ADD_LOCATION) != null ? 
							returnQueryWinAdditionalData(postUrl, JS_ADD_LOCATION).toString() :
								"None";

		return location.contentEquals("null") ? "None" : location;
	}

	default long getPostTimeStamp(final String postUrl) {
		return returnQueryWinData(postUrl, JS_TIMESTAMP) != null ?
				((Number)returnQueryWinData(postUrl, JS_TIMESTAMP)).longValue() : 
					returnQueryWinAdditionalData(postUrl, JS_ADD_TIMESTAMP) != null ? 
							((Number)returnQueryWinAdditionalData(postUrl, JS_ADD_TIMESTAMP)).longValue() :
								-1;
	}


}
