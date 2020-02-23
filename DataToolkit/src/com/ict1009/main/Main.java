package com.ict1009.main;

import org.json.JSONObject;

import com.ict1009.stanfordcorenlp.InstagramSentimentAnalyzer;
import com.ict1009.userinterface.FrameDashboard;
import com.ict1009.utilities.TimeStampConverter;

public class Main {

	/**
	 * The entry point of the program
	 */
	public static void main(String[] args) {
		//Launches the main GUI of the program
		FrameDashboard.launchGui(args);
//		JSONObject obj = new JSONObject("{\"has_public_page\":true,\"name\":\"Trump Tower New York\",\"id\":\"241968552\",\"slug\":\"trump-tower-new-york\",\"address_json\":\"{\\\"street_address\\\": \\\"725 5th Avenue\\\", \\\"zip_code\\\": \\\"10022\\\", \\\"city_name\\\": \\\"New York, New York\\\", \\\"region_name\\\": \\\"\\\", \\\"country_code\\\": \\\"US\\\", \\\"exact_city_match\\\": false, \\\"exact_region_match\\\": false, \\\"exact_country_match\\\": false}\"}");
//		System.out.println(obj.getString("name"));
//		obj.getString("name");
	}
}