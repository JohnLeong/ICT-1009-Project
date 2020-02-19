package com.ict1009.scrapper;

import org.json.JSONObject;

public interface InstagramDryScraping {
	
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
	
	default Object returnQueryObject(final String postUrl, final String query) {
		return (new JSONObject(WindowSharedDataExtractor.getWindowSharedDataJson(postUrl))).optQuery(query);
	}
	
	/**
	 * Gets the number of comments from the post at the specified URL
	 * 
	 * @param posturl	The URL of the post
	 * @return			The number of comments in the post
	 */
	default long getNumberOfComments(final String posturl) { 
		return returnQueryObject(posturl, JS_NUM_OF_COMMENTS) != null ?
				((Number)returnQueryObject(posturl, JS_NUM_OF_COMMENTS)).longValue() :
					-1;
	}
	
	/**
	 * Gets the number of likes from the post at the specified URL
	 * 
	 * @param postUrl	The URL of the post
	 * @return			The number of likes in the post
	 */
	default long getNumberOfLikes(final String postUrl) {
		return returnQueryObject(postUrl, JS_NUM_OF_LIKES) != null ?
				((Number)returnQueryObject(postUrl, JS_NUM_OF_LIKES)).longValue() : 
					-1;
	}
	
	/**
	 * Gets the number of views from the video post at the specified URL
	 * 
	 * @param postUrl	The URL of the video post
	 * @return			The number of views of the video post
	 */
	default long getNumberOfVideoViews(final String postUrl) {
		return returnQueryObject(postUrl, JS_NUM_OF_VIDEO_VIEWS) != null ? 
				((Number)returnQueryObject(postUrl, JS_NUM_OF_VIDEO_VIEWS)).longValue() : 
					-1;
	}

	/**
	 * Checks if the post at the specified URL is a video
	 * 
	 * @param postUrl	The URL of the post to check
	 * @return			Returns true if the post is a video
	 */
	default boolean getIsVideo(final String postUrl) {
		return ((Boolean)returnQueryObject(postUrl, JS_IS_VIDEO)).booleanValue();
	}

	/**
	 * Gets the URL of the video from a post
	 * 
	 * @param postUrl	The URL of the post
	 * @return			The URL of the video
	 */
	default String getVideoUrl(final String postUrl) {
		return returnQueryObject(postUrl, JS_VIDEO_URL) != null ? 
				returnQueryObject(postUrl, JS_VIDEO_URL).toString() : 
					"None";
	}
	
	/**
	 * Gets the URL of the image from a post
	 * 
	 * @param postUrl	The URL of the post
	 * @return			The URL of the image
	 */
	default String getDisplayImageUrl(final String postUrl) {
		return returnQueryObject(postUrl, JS_IMAGE_DISPLAY_URL) != null ?
				returnQueryObject(postUrl, JS_IMAGE_DISPLAY_URL).toString() :
					"None";
	}
	
	/**
	 * Gets the caption of the post at the specified URL
	 * 
	 * @param postUrl	The URL of the post
	 * @return			The caption of the post
	 */
	default String getPostCaption(final String postUrl) {
		return returnQueryObject(postUrl, JS_CAPTION) != null ?
				returnQueryObject(postUrl, JS_CAPTION).toString() :
					"None";
	}

}
