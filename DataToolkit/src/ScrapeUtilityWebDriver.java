import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class ScrapeUtilityWebDriver extends ScrapeUtility {

	protected WebDriver driver;
	final private static String GECKO_DRIVER_PATH = System.getProperty("user.dir") + "\\geckodriver.exe";
	private JavascriptExecutor js;

	public ScrapeUtilityWebDriver(String defaultURL) {
		super(defaultURL);
		System.setProperty("webdriver.gecko.driver",GECKO_DRIVER_PATH);
		this.driver= new FirefoxDriver();
	}
	
	protected WebElement waitUntilSelectorLoads(String cssQuery, int seconds) {
		WebElement element = 
				(new WebDriverWait(driver, seconds))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssQuery)));
		return element;
	}

	public void browseToUrl(String url) {
		this.driver.get(url);
	}
	public void browseToUrl() {
		this.driver.get(this.defaultUrl);
	}

	public void scrollDownByPixels(int pixels) {
		this.js = (JavascriptExecutor)driver;
		this.js.executeScript("window.scrollBy(0, "+ pixels +")");	
	}

	public void scrollToVisibleElement(WebElement element) {
		this.js = (JavascriptExecutor)driver;
		this.js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollToPageBottom() {
		this.js = (JavascriptExecutor)driver;
		this.js.executeScript("window.scrollTo(0, document.body.scrollHeight)");	
	}

	public boolean forceWaitInMiliseconds(int ms) {
		try {
			Thread.sleep(ms);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void waitInSeconds(int seconds) {

		this.driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
}







