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
	final String JS_IMAGE_DISPLAY_URL		="/entry_data/PostPage/0/graphql/shortcode_media/display_url";
	
	default Object returnQueryObject(final String postUrl, final String query) {
		return (new JSONObject(WindowSharedDataExtractor.getWindowSharedDataJson(postUrl))).optQuery(query);
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
}
