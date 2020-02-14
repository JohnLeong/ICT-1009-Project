import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterScraper extends ScrapeUtility{
	
	private final static String keyOAuthConsumer = "6boiTZNAxirQtcA78pxyBSeFz";
	private final static String keyOAuthConsumerSecret = "ZjULCLPP2J00zcGU0eu9HDItzfVQvT07G3WD5jDyT1ociJ52ZL";
	private final static String keyOAuthAccessToken = "1220269199634780161-oebkqUnf3szbGozkjm8h11pbYbj63Y";
	private final static String keyOAuthAccessTokenSecret = "t9jScqV381nsZMUMckNcFCoMzFSLWJ6KwVJ6M3cKXBesv";

	public TwitterScraper(String url) {
		super(url);
	}
	
	@Override
	public ScrapeCode scrapeByHashTags(final String loginId, final String loginPassword,
			final String joinedHashTags, final long numberOfPosts, final String savePath) {
		Twitter twitter = createTwitterInstance();
		
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
	            return ScrapeCode.SCRAPE_ERROR;
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
				post.put("caption", DataCleansing
						.dataCleanse(tweet.getText())
						); 
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
		    				comment.put("desc", DataCleansing.dataCleanse(
		    						reply.getText())
		    						);
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
		results.put("details", allHashTagsDetails);
		
		exportJsonObjToFolder(results, savePath);
		
		return ScrapeCode.SUCCESS;
	}
	
	
	@Override
	public ScrapeCode scrapeByProfiles(final String loginId, final String loginPassword,
			final String joinedProfileNames, final long numberOfPosts, final String savePath) {
		Twitter twitter = createTwitterInstance();
		
		String[] profileNames = joinedProfileNames.split(DELIM_HASHTAGS);
		JSONArray allHashTagsDetails = new JSONArray();
		
		for (int i = 0; i < profileNames.length; ++i) {
			List<Status> results = new ArrayList<Status>();
			try {   
			    //Get posts from user
				Paging paging = new Paging(1, (int)Math.min((int)numberOfPosts, 100));
				results.addAll(twitter.getUserTimeline(profileNames[i], paging));
			}
			catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Error when finding tweets: " + te.getMessage());	      
	            return ScrapeCode.SCRAPE_ERROR;
	        }
			
			/**
			 * Process all posts and append into JSONObject
			 */
			JSONObject profileInfo = new JSONObject();
			JSONArray allPosts = new JSONArray();

			profileInfo.put("profile_name", profileNames[i]);

		    for (Status tweet : results) {
				JSONObject post = new JSONObject();
				JSONArray commentsList = new JSONArray();
				allPosts.put(post);
				post.put("posted_by", tweet.getUser().getName());
				post.put("caption", DataCleansing.dataCleanse(tweet.getText())); 
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
		    				comment.put("desc", DataCleansing.dataCleanse(reply.getText()));
		                	commentsList.put(comment);
		                }
		            }

			    } catch (Exception e) {
			    	System.out.println(e.getMessage());
			        continue;
			    }
				post.put("comments", commentsList);		
		    }
		
			profileInfo.put("extracted_posts", allPosts);
			allHashTagsDetails.put(profileInfo);
		}
		
		JSONObject results = new JSONObject();
		results.put("platform", "twitter");
		results.put("scrape_mode", "profiles");
		results.put("details", allHashTagsDetails);
		
		exportJsonObjToFolder(results, savePath);
		
		return ScrapeCode.SUCCESS;
	}
	
	private Twitter createTwitterInstance() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(TwitterScraper.keyOAuthConsumer)
		  .setOAuthConsumerSecret(TwitterScraper.keyOAuthConsumerSecret)
		  .setOAuthAccessToken(TwitterScraper.keyOAuthAccessToken)
		  .setOAuthAccessTokenSecret(TwitterScraper.keyOAuthAccessTokenSecret);

		return (new TwitterFactory(cb.build()).getInstance());
	}
}
