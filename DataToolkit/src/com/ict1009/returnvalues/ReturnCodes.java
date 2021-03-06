package com.ict1009.returnvalues;

public interface ReturnCodes {
	public enum ScrapeCode {
		SUCCESS(1, "Successfully finished scraping process.\n"),
		LOGIN_FAIL (0, "Failed to login. Please check if your account is valid.\n"),
		PAGE_TIMEOUT(-1, "Page timeout! Please check your internet connection.\n"),
		MISSING_ELEMENT(-2, "Unable to find desired elements.\n"),
		SCRAPE_ERROR(-3, "Error. Unable to scrape.\n"),
		POSTS_SCRAPE_ERROR(-4, "Failed to scrape posts contents into file\n");
		
		private final int code;
		private final String description;

		public int getCode() {
			return this.code;
		}
		
		public String getDescription() {
			return this.description;
		}
		
		private ScrapeCode(int code, String description) {
			this.code = code;
			this.description = description;
		}
	}
}
