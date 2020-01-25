import java.util.ArrayList;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List; 


public class InstagramScraper extends ScrapeUtility{
	
	private ArrayList<String> subUrls;
	
	public InstagramScraper(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	protected ArrayList<String> getHashTagPostsSubUrls() {
		ArrayList<String> subUrls = new ArrayList<String>();	
		String doc = super.driver.getPageSource();
		if (doc != null && !doc.isEmpty()) {
			Elements postsLink = Jsoup.parse(doc).select(".KC1QD").select("a[href]");
			for (Element ele : postsLink) {
				
				System.out.println(ele.attr("href"));
			}
		} 
		return subUrls;
	}
	
	private void typeloginCredentials(String userName, String password) {
		
	}
	
	public void launchScrapeProcedure(String loginUrl) {
		super.browseToUrl(loginUrl);
		
		
		//Wait until the user id text box label to load first before getting page source
		if (waitUntilElementLoads("._9nyy2") == null) {
			System.out.println("TimeoUt element not found");
		}
		
		Document doc = Jsoup.parse(super.driver.getPageSource());
		WebElement userName = super.driver.findElement(By.cssSelector(".HmktE input[name='username']"));
		WebElement password = super.driver.findElement(By.cssSelector(".HmktE input[name='password']"));
		WebElement loginButton = super.driver.findElement(By.cssSelector(".HmktE button[type='submit']"));
		userName.sendKeys("hehebongesh");
		password.sendKeys("Password12345");
		loginButton.click();
		
		//Wait until go back to home page
		new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://www.instagram.com/"));
	
		super.browseToUrl("https://www.instagram.com/explore/tags/apples/");
		


		
	}
	
	
	
	
	
	
	
	
}
