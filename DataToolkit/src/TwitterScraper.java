import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TwitterScraper extends ScrapeUtility {

	public TwitterScraper(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void launchScrapeProcedure(String loginUrl) {
		super.browseToUrl("https://twitter.com/search?q=%23WuhanCoronavirus&src=typed_query");
				
		String selectorQuery = "li[data-item-type='tweet'] .content .tweet-text";
		
		//Wait for the page to load the main content
		//Test for page style 1
		try {
			if(waitUntilElementLoads(".content-main") == null) {
				
			}
		}
		catch(Exception e) {
			
		}
		
		//Test for page style 2
		try {
			selectorQuery = "div[data-testid='tweet'] .r-hkyrab";
			if(waitUntilElementLoads("div[data-testid='primaryColumn']") != null) {
				System.out.print("here");
			}
		}
		catch(Exception e) {
			
		}
		
		
		List<WebElement> elementList = super.driver.findElements(By.cssSelector(selectorQuery));
		for(WebElement element : elementList) {
			TwitterPost post = new TwitterPost(element.getText());
			postList.add(post);
		}
		
		printAllPosts();
		
		//System.out.print(this.driver.getPageSource());
		
		//Don't remove the code below! We might use it in the future
		/*ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("TgHNMa7WZE7Cxi1JbkAMQ")
		  .setOAuthConsumerSecret("SHy9mBMBPNj3Y17et9BF4g5XeqS4y3vkeW24PttDcY")
		  .setOAuthAccessToken("your access token")
		  .setOAuthAccessTokenSecret("your access token secret");

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		//Twitter twitter = new TwitterFactory().getInstance();
		//twitter.setOAuthConsumer("7YBPrscvh0RIThrWYVeGg", "sMO1vDyJ9A0xfOE6RyWNjhTUS1sNqsa7Ae14gOZnw");
        try {
            Query query = new Query("source:twitter4j yusukey");
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }*/
	}
}
