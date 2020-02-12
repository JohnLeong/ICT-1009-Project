import org.json.JSONArray;
import org.json.JSONObject;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterScraper extends ScrapeUtility{

	public TwitterScraper(String url) {
		super(url);
	}
	
	@Override
	public ReturnCode scrapeByHashTags(final String loginId, final String loginPassword,
			final String joinedHashTags, final long numberOfPosts, final String savePath) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("6boiTZNAxirQtcA78pxyBSeFz")
		  .setOAuthConsumerSecret("ZjULCLPP2J00zcGU0eu9HDItzfVQvT07G3WD5jDyT1ociJ52ZL")
		  .setOAuthAccessToken("1220269199634780161-oebkqUnf3szbGozkjm8h11pbYbj63Y")
		  .setOAuthAccessTokenSecret("t9jScqV381nsZMUMckNcFCoMzFSLWJ6KwVJ6M3cKXBesv");

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		
		String[] hashTags = joinedHashTags.split(DELIM_HASHTAGS);
		JSONArray allHashTagsDetails = new JSONArray();
		
		for (int i = 0; i < hashTags.length; ++i) {
			QueryResult result;
			try {
				//Create a new query for a given hashtag
			    Query query = new Query(hashTags[i].charAt(0) == '#' ? hashTags[i] : '#' + hashTags[i]);
			    query.setCount((int)numberOfPosts);
			    result = twitter.search(query);
			}
			catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Error when finding tweets: " + te.getMessage());	      
	            return ReturnCode.SCRAPE_ERROR;
	        }
			

			/**
			 * Process all posts and append into JSONObject
			 */
			JSONObject hashTagPageInfo = new JSONObject();
			JSONArray allPosts = new JSONArray();

			hashTagPageInfo.put("hash_tag", hashTags[i]);

		    for (Status tweet : result.getTweets()) {
				JSONObject post = new JSONObject();
				JSONArray commentsList = new JSONArray();
				allPosts.put(post);
				post.put("posted_by", tweet.getUser().getName());
				post.put("caption", tweet.getText()); 
				post.put("no_of_likes", tweet.getFavoriteCount());
				post.put("no_of_retweets", tweet.getRetweetCount());
				post.put("date_time", tweet.getCreatedAt().toString());
				
				//Get all replies
				try {
			        Query query = new Query("to:" + tweet.getUser().getName() + " since_id:" + tweet.getId());
			        QueryResult replies;

		        	replies = twitter.search(query);
		            for (Status reply : replies.getTweets()) {
		                if (reply.getInReplyToStatusId() == tweet.getId()) {
		                	JSONObject comment = new JSONObject();
		                	comment.put("user", reply.getUser().getName()); 
		    				comment.put("desc", reply.getText());
		                	commentsList.put(comment);
		                }
		            }

			    } catch (Exception e) {
			    	System.out.println(e.getMessage());
			        continue;
			    }
				post.put("comments", commentsList);			
		    }
		
			hashTagPageInfo.put("extracted_posts", allPosts);
			allHashTagsDetails.put(hashTagPageInfo);
		}
		
		JSONObject results = new JSONObject();
		results.put("platform", "twitter");
		results.put("scrape_mode", "hashtags");
		results.put("hash_tags_details", allHashTagsDetails);
		
		exportJsonObjToFolder(results, savePath);
		
		return ReturnCode.SUCCESS;
	}
}
