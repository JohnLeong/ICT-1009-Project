package com.ict1009.visualisation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ict1009.utilities.JSONUtility;



public class CheckerFile {
	
public boolean CheckerFiles(String file) {
	boolean format =false;
	try {
	
	JSONObject jsonObject = new JSONObject(JSONUtility.parseJSONToString(file));
	JSONArray records = jsonObject.getJSONArray("details");
	for (int x = 0; x < records.length(); x++) {
		int totalPost = records.getJSONObject(x).getInt("total_posts"); 
		if (totalPost > 0) {
			format = true;
		}
		
	}
	}
	catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return format;
}


}
