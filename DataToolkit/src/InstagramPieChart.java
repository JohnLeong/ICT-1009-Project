import javax.swing.JPanel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class InstagramPieChart extends ApplicationFrame {

	static HashMap<String, Integer> data = new HashMap();

	// Number of Post per location
	public InstagramPieChart(String title) {
		super(title);
		setContentPane(createDemoPanel());
	}

	private static PieDataset createDataset() {
		int count;
		String name;
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (String key : data.keySet()) { // To iterate through the dictionary and push in the value and location to
											// the piechart.
			name = key;
			count = data.get(key);
			dataset.setValue(name, count);
		}
		return dataset;

	}

	private static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("Number of Post & Location", // chart title
				dataset, // data
				true, // include legend
				true, false);

		return chart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}

	public static char getCharFromString(String str, int index) {
		return str.charAt(index);
	}
	

	public void ReadingJson(String jFile) {
		String jsonFile = jFile;

		try {
			JSONObject jsonObject = new JSONObject(JSONUtility.parseJSONToString(jsonFile));
			JSONArray records = jsonObject.getJSONArray("details");
			String keyToBeChecked = null;
			boolean isKeyPresent;
			int value = 0, count = 0;
			for (int x = 0; x < records.length(); x++) {// details of json object and get json array from extractedposts
				JSONArray extractData = records.getJSONObject(x).getJSONArray("extracted_posts");

				for (int y = 0; y < extractData.length(); y++) {
					JSONObject extracted = extractData.getJSONObject(y);
					String loc = extracted.getString("location");
					
					count = data.containsKey(loc) ? data.get(loc) : 0;
					data.put(loc, count + 1);
					
					
//					if (insta.isEmpty()) { // This is to check if the dictionary is empty.
//						insta.put(loc, value + 1);// insert key and value into the dictionary to count number of times
//													// it appears on the same location.
//					} else {// Check if there is same key present on the dictionary.
//						keyToBeChecked = loc; // Used this variable to checked for key in the dictionary.
//						isKeyPresent = insta.containsKey(keyToBeChecked);
//						if (isKeyPresent == false) { // Insert key and value if there is no same in the dictionary.
//							insta.put(keyToBeChecked, value + 1);
//						} else if (isKeyPresent == true) {// If there is existing key in the dictionary store pervious
//															// value in OldValue and replace new value to increment the
//															// count.
//							int oldValue = insta.get(keyToBeChecked);
//							insta.put(keyToBeChecked, 1 + oldValue);
//						}
//					}
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

	}
}