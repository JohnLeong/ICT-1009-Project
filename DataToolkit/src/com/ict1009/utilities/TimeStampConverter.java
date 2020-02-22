package com.ict1009.utilities;

import java.util.Date;

public class TimeStampConverter {
	public static String timeStampToDate(long timeStamp) {
		try {
			Date d = new Date(timeStamp);
			String formattedDate = d.toGMTString();
			return formattedDate;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error";
		}
	}
}
