package com.ict1009.visualisation;
import javax.swing.JPanel;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ict1009.utilities.JSONUtility;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;

public class InstagramPieChart extends ApplicationFrame {

	private static final long serialVersionUID = 1L;
	static HashMap<String, Integer> data = new HashMap<String, Integer>();

	// Number of Post per location
	public InstagramPieChart(String title) {
		super(title);
		setContentPane(createDemoPanel());
	}

	/**
	 * Creates a dataset of post locations used to populate a chart
	 * 
	 * @return		The populated dataset
	 */
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

	/**
	 * Creates and populates a chart from the specified dataset
	 * 
	 * @param dataset		The dataset to populate the chart with
	 * @return				The created chart
	 */
	private static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("Number of Post & Location", // chart title
				dataset, // data
				true, // include legend
				true, false);

		return chart;
	}

	/**
	 * Creates the chart panel
	 * 
	 * @return		The created chart panel
	 */
	public static JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}

	/**
	 * Reads a JSON string for a scraped dataset and checks the location of each post
	 * 
	 * @param jFile		The JSON string to read
	 */
	public void ReadingJson(String jFile) {
		String jsonFile = jFile;

		try {
			JSONObject jsonObject = new JSONObject(JSONUtility.parseJSONToString(jsonFile));
			JSONArray records = jsonObject.getJSONArray("details");
			//String keyToBeChecked = null;
			//boolean isKeyPresent;
			//int value = 0;
			int count = 0;
			for (int x = 0; x < records.length(); x++) {// details of json object and get json array from extractedposts
				JSONArray extractData = records.getJSONObject(x).getJSONArray("extracted_posts");

				for (int y = 0; y < extractData.length(); y++) {
					JSONObject extracted = extractData.getJSONObject(y);
					String loc = extracted.getString("location");
					
					count = data.containsKey(loc) ? data.get(loc) : 0;
					data.put(loc, count + 1);
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