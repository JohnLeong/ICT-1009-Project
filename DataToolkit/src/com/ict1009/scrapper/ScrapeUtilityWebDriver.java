package com.ict1009.scrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.Test;

public abstract class ScrapeUtilityWebDriver extends ScrapeUtility {

	protected WebDriver driver;
	final private static String GECKO_DRIVER_PATH = System.getProperty("user.dir") + "\\geckodriver.exe";
	private JavascriptExecutor js;

	public ScrapeUtilityWebDriver(String defaultURL) {
		super(defaultURL);
		System.setProperty("webdriver.gecko.driver",GECKO_DRIVER_PATH);
		this.driver= new FirefoxDriver();
	}
	
	/**
	 * Wait for X second intervals until the specified web element is loaded
	 * 
	 * @param cssQuery	The css selector of the web element to wait for
	 * @param seconds	The time interval between each wait
	 * @return			The loaded web element
	 */
	protected WebElement waitUntilSelectorLoads(String cssQuery, int seconds) {
		WebElement element = 
				(new WebDriverWait(driver, seconds))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssQuery)));
		return element;
	}
	
	/**
	 * Gets a specified web element safely by waiting until it loads
	 * 
	 * @param cssSelector		The web element to get
	 * @param timeOutSeconds	The max amount of time to wait
	 * @return					The loaded web element
	 */
	protected WebElement safeGetWebElement(final String cssSelector, final int timeOutSeconds) {
		try {
			this.waitUntilSelectorLoads(cssSelector, timeOutSeconds);
			return driver.findElement(By.cssSelector(cssSelector));
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Gets a list of specified web elements safely by waiting until it loads
	 * 
	 * @param cssSelector		The web elements to get
	 * @param timeOutSeconds	The max amount of time to wait
	 * @return					The loaded web element list
	 */
	protected List<WebElement> safeGetWebElements(final String cssSelector,
			final int timeOutSeconds) {
		try {
			this.waitUntilSelectorLoads(cssSelector, timeOutSeconds);
			return driver.findElements(By.cssSelector(cssSelector));
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * Checks if the specified web element exists
	 * 
	 * @param cssQuery		The css selector of the element to look for
	 * @return				Returns true if the web element exists
	 */
	protected boolean cssSelectorExists(String cssQuery) {
		return driver.findElements(By.cssSelector(cssQuery)).size() != 0;
	}

	/**
	 * Sets the webdriver to browse to the specified URL
	 * 
	 * @param url		The URL to browse to
	 */
	public void browseToUrl(String url) {
		this.driver.get(url);
	}
	
	/**
	 * Sets the webdriver to browse to the default URL
	 */
	public void browseToUrl() {
		this.driver.get(this.defaultUrl);
	}

	/**
	 * Scrolls the webpage down by a specified number of pixels
	 * 
	 * @param pixels	The number of pixels to scroll down by
	 */
	public void scrollDownByPixels(int pixels) {
		this.js = (JavascriptExecutor)driver;
		this.js.executeScript("window.scrollBy(0, "+ pixels +")");	
	}

	/**
	 * Scrolls the webpage until the specified web element is visible
	 * 
	 * @param element	The web element to look for
	 */
	public void scrollToVisibleElement(WebElement element) {
		this.js = (JavascriptExecutor)driver;
		this.js.executeScript("arguments[0].scrollIntoView();", element);
	}

	/**
	 * Scrolls the webpage to the bottom
	 */
	public void scrollToPageBottom() {
		this.js = (JavascriptExecutor)driver;
		this.js.executeScript("window.scrollTo(0, document.body.scrollHeight)");	
	}

	/**
	 * Forces a wait by sleeping the current thread by a specified number of mili seconds
	 * 
	 * @param ms	The amount of time to wait for
	 * @return		Returns true if the thread is able to sleep
	 */
	public boolean forceWaitInMiliseconds(int ms) {
		try {
			Thread.sleep(ms);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/** 
	 * Wait for a specified number of seconds
	 * 
	 * @param seconds	The number of seconds to wait for
	 */
	public void waitInSeconds(int seconds) {

		this.driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
}








