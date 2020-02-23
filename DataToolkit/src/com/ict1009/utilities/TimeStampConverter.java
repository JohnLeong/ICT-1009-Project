package com.ict1009.utilities;

import java.text.SimpleDateFormat;

public class TimeStampConverter {
	private static String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	public static String timeStampToDate(long timeStamp) {
		try {
			return (new SimpleDateFormat(DATE_FORMAT_PATTERN)).format(timeStamp);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
}
