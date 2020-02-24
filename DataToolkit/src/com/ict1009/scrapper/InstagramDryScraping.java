package com.ict1009.scrapper;

import org.json.JSONObject;

public interface InstagramDryScraping {

	//Add datetime and location 
	/**
	 *  Constant strings to be used with JSONQuery to retrieve JSON fields  
	 */
	final String JS_ENTRY_DIR					= "/entry_data/PostPage/0";

	final String JS_ADD_NUM_OF_LIKES 			= "/graphql/shortcode_media/edge_media_preview_like/count";	
	final String JS_ADD_IS_VIDEO 				= "/graphql/shortcode_media/is_video";	
	final String JS_ADD_NUM_OF_VIDEO_VIEWS 		= "/graphql/shortcode_media/video_view_count/";	
	final String JS_ADD_NUM_OF_COMMENTS 		= "/graphql/shortcode_media/edge_media_to_parent_comment/count";	
	final String JS_ADD_VIDEO_URL 				= "/graphql/shortcode_media/video_url";	
	final String JS_ADD_IMAGE_DISPLAY_URL		= "/graphql/shortcode_media/display_url";
	final String JS_ADD_CAPTION 				= "/graphql/shortcode_media/edge_media_to_caption/edges/0/node/text";
	final String JS_ADD_TIMESTAMP 				= "/graphql/shortcode_media/taken_at_timestamp"; 		
	final String JS_ADD_LOCATION 				= "/graphql/shortcode_media/location";	


	final String JS_NUM_OF_LIKES 			= JS_ENTRY_DIR + JS_ADD_NUM_OF_LIKES;
	final String JS_IS_VIDEO 				= JS_ENTRY_DIR + JS_ADD_IS_VIDEO;
	final String JS_NUM_OF_VIDEO_VIEWS 		= JS_ENTRY_DIR + JS_ADD_NUM_OF_VIDEO_VIEWS;	
	final String JS_NUM_OF_COMMENTS 		= JS_ENTRY_DIR + JS_ADD_NUM_OF_COMMENTS;
	final String JS_VIDEO_URL 				= JS_ENTRY_DIR + JS_ADD_VIDEO_URL;
	final String JS_IMAGE_DISPLAY_URL		= JS_ENTRY_DIR + JS_ADD_IMAGE_DISPLAY_URL;
	final String JS_CAPTION 				= JS_ENTRY_DIR + JS_ADD_CAPTION;
	final String JS_TIMESTAMP 				= JS_ENTRY_DIR + JS_ADD_TIMESTAMP;
	final String JS_LOCATION 				= JS_ENTRY_DIR + JS_ADD_LOCATION;	

	final String RET_EMPTY_STRING				= "None";	
	final long RET_INVALID_NUMBER				= -1;


	default Object returnQueryWinData(final String postUrl, final String query) {
		return (new JSONObject(JsoupUtility
				.getWindowSharedDataJson(postUrl))
				).optQuery(query);
	}

	default Object returnQueryWinAdditionalData(final String postUrl, final String query) {
		return (new JSONObject(JsoupUtility.getAditonalWindowSharedDataJson(postUrl))).optQuery(query);
	}

	default Object tryGetNotNullJsonQuery(final String url, final String jsonSelector) {
		Object obj = returnQueryWinData(url, jsonSelector);
		return obj != null ? obj : returnQueryWinAdditionalData(url, jsonSelector.substring(JS_ENTRY_DIR.length()));
	}
	
	/**
	 * Gets the number of comments from the post at the specified URL
	 * 
	 * @param posturl	The URL of the post
	 * @return			The number of comments in the post
	 */
	default long getNumberOfComments(final String posturl) { 
		Object obj = tryGetNotNullJsonQuery(posturl, JS_NUM_OF_COMMENTS);
		return obj != null ? ((Number)obj).longValue() : RET_INVALID_NUMBER;

	}


	/**
	 * Gets the number of likes from the post at the specified URL
	 * 
	 * @param postUrl	The URL of the post
	 * @return			The number of likes in the post
	 */
	default long getNumberOfLikes(final String postUrl) {
		Object obj = tryGetNotNullJsonQuery(postUrl, JS_NUM_OF_LIKES);
		return obj != null ? ((Number)obj).longValue() : RET_INVALID_NUMBER;
	}

	/**
	 * Gets the number of views from the video post at the specified URL
	 * 
	 * @param postUrl	The URL of the video post
	 * @return			The number of views of the video post
	 */
	default long getNumberOfVideoViews(final String postUrl) {
		Object obj = tryGetNotNullJsonQuery(postUrl, JS_NUM_OF_VIDEO_VIEWS);
		return obj != null ? ((Number)obj).longValue() : RET_INVALID_NUMBER;
	}

	/**
	 * Checks if the post at the specified URL is a video
	 * 
	 * @param postUrl	The URL of the post to check
	 * @return			Returns true if the post is a video
	 */
	default boolean getIsVideo(final String postUrl) {
		Object obj = tryGetNotNullJsonQuery(postUrl, JS_IS_VIDEO);
		return obj != null ? ((Boolean)obj).booleanValue() : false;
	}

	/**
	 * Gets the URL of the video from a post
	 * 
	 * @param postUrl	The URL of the post
	 * @return			The URL of the video
	 */
	default String getVideoUrl(final String postUrl) {
		Object obj = tryGetNotNullJsonQuery(postUrl, JS_VIDEO_URL);
		return obj != null ? obj.toString() : RET_EMPTY_STRING;
	}

	/**
	 * Gets the URL of the image from a post
	 * 
	 * @param postUrl	URL of the post
	 * @return			URL of the image
	 */
	default String getDisplayImageUrl(final String postUrl) {
		Object obj = tryGetNotNullJsonQuery(postUrl, JS_IMAGE_DISPLAY_URL);
		return obj != null ? obj.toString() : RET_EMPTY_STRING;
	}

	/**
	 * Gets the caption of the post at the specified URL
	 * 
	 * @param postUrl	URL of the post
	 * @return			Caption of the post
	 */
	default String getPostCaption(final String postUrl) {
		Object obj = tryGetNotNullJsonQuery(postUrl, JS_CAPTION);
		return obj != null ? obj.toString() : RET_EMPTY_STRING;
	}

	/**
	 * Get the location of post 
	 * @param postUrl	URL of post
	 * @return			Location of post
	 */
	default String getPostLocation(final String postUrl) {
		Object obj = tryGetNotNullJsonQuery(postUrl, JS_LOCATION);
		if (obj != null) {
			return obj.toString().contentEquals("null") ? RET_EMPTY_STRING : obj.toString();
		}
		return RET_EMPTY_STRING;
	}

	/**
	 * Get timestamp of post
	 * @param postUrl	URL of post
	 * @return			Timestamp of post in long format
	 */
	default long getPostTimeStamp(final String postUrl) {
		Object obj = tryGetNotNullJsonQuery(postUrl, JS_TIMESTAMP);
		return obj != null ? ((Number)obj).longValue() : RET_INVALID_NUMBER;
	}


}
