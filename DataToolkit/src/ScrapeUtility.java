import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait; //FluentWait is a Class and it is a part of this package
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ScrapeUtility {
	
	protected WebDriver driver;
	protected String defaultUrl;	
	final private static String GECKO_DRIVER_PATH = System.getProperty("user.dir") + "\\geckodriver.exe";
	protected List<SocialMediaPost> postList = new ArrayList<SocialMediaPost>();
	
	public ScrapeUtility(String url) {
		System.setProperty("webdriver.gecko.driver",GECKO_DRIVER_PATH);
		this.defaultUrl = url; 	
		this.driver= new FirefoxDriver();
	}
		
	
	protected WebElement waitUntilElementLoads(String cssQuery) {
//		 FluentWait<WebDriver> fluentWait = 
//				 new FluentWait<WebDriver>(this.driver)
//			        .withTimeout(Duration.ofSeconds(20))
//			        .pollingEvery(Duration.ofMillis(200))
//			        .ignoring(NoSuchElementException.class);
		 
		 
		 WebElement element = (new WebDriverWait(driver, 10))
				 .until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssQuery)));
		 //"._9nyy2"
		 return element;
	}
	
	public void browseToUrl(String url) {
		this.driver.get(url);
	}
	public void browseToUrl() {
		this.driver.get(this.defaultUrl);
	}

	public void launchScrapeProcedure(String loginUrl) {
		
	}
	
	/**
	* Prints out all the posts that have been scraped
	*/
	public void printAllPosts() {
		for(SocialMediaPost post : postList) {
			System.out.println("********************************");
			System.out.println(post.getText());
		}
	}
}
	
	
	
	
	
	
	

