import org.json.JSONArray;
import org.json.JSONObject;

public class DataCleanser {
 public static JSONObject dataCleanse(JSONObject scrapeData) {
	//remove multiple spaces
	JSONArray extractedData =  scrapeData.getJSONArray("extracted_posts");
	
	for (int i = 0; i < extractedData.length(); i++ ) {
		StringBuilder postCaption= new StringBuilder(extractedData.getJSONObject(i).getString("caption")) ;
		boolean spaceReached = false; 
		for (int j = postCaption.length() -1; j>-1; j-- ) {
			if (postCaption.charAt(j) == ' ') {
				if (spaceReached == true) {
					postCaption.deleteCharAt(j);
				}	
				else {
					spaceReached = false;
				}
			}
			else {
				spaceReached = false;
			}
		
		}
		extractedData.getJSONObject(i).put("caption", postCaption.toString());
	}
	
	//remove unecessary character
	 
	 return scrapeData;
 }
 
}
