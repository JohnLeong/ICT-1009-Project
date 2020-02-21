package com.ict1009.visualisation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.ejml.simple.SimpleMatrix;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ict1009.stanfordcorenlp.InstagramSentimentAnalyzer;
import com.ict1009.stanfordcorenlp.Pipeline;
import com.ict1009.utilities.JSONUtility;
import org.jfree.data.general.PieDataset;

import edu.stanford.nlp.ling.CoreAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreNLPProtos.Sentiment;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentClass;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;



public class InstagramSentimentPie extends JFrame {

	private static final long serialVersionUID = 1L;
	static HashMap<String, Integer> data = new HashMap<String, Integer>();
	static HashMap<String, Integer> descrip = new HashMap<String, Integer>();
	static double negatives;
    static double positives;
    static double neutral;
   
	static int numberOfReplies;

	public InstagramSentimentPie(String title) {
		super(title);
		// Create dataset	
		setContentPane(createDemoPanel( ));
			   
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Creates a dataset of post sentiments used to populate a chart
	 * 
	 * @return		The populated dataset
	 */
	private static PieDataset createDataset() {
			
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Positive", positives);
		dataset.setValue("Negative", negatives);
		dataset.setValue("Neutral", neutral);

		return dataset;
	}
	public static JPanel createDemoPanel() {
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}
	
	private static JFreeChart createChart(PieDataset dataset) {
		
		JFreeChart chart = ChartFactory.createPieChart("Sentement Analysis", // chart title
				dataset, // data
				true, // include legend
				true, false);

		return chart;
	}

	static boolean checkedCharacters(String x, String y) {
		int n = x.length();
		for (int i = 0; i < n; i++)
			if (x.charAt(i) != y.charAt(i))
				return false;
		return true;
	}

	/**
	 * Reads a JSON string for a scraped dataset and checks the sentiment of each post
	 * 
	 * @param jFile		The JSON string to read
	 */

	public void ReadingJson(String jsonFilePath) {
		String jsonFile = jsonFilePath;
		String desc;
		try {
			JSONObject jsonObject = new JSONObject(JSONUtility.parseJSONToString(jsonFile));
			JSONArray records = jsonObject.getJSONArray("details");
			
			for (int x = 0; x < records.length(); x++) {// details of json object and get json array from extractedposts
				JSONArray extractData = records.getJSONObject(x).getJSONArray("extracted_posts");
				for (int y = 0; y < extractData.length(); y++) {
					//JSONObject extracted = extractData.getJSONObject(y);
					//String dateTime = extracted.getString("date_time");			
					JSONArray comment = extractData.getJSONObject(y).getJSONArray("comments");
					//System.out.println(extractData.getJSONObject(y));
					for (int i = 0; i < comment.length(); i++) {
                    	JSONObject row = comment.getJSONObject(i);
                    	desc = row.getString("desc");
                    	descrip.put(desc, i);
                    	numberOfReplies++;               
		     }

		}
				}
			scaledwnDescription(descrip);

			}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void scaledwnDescription(HashMap<String, Integer> descrip) {
		Sentiment2 method2 = new Sentiment2();
		
		for(String keys : descrip.keySet()) {
			method2.StanfordAnallysis(keys);
		}
		negatives =method2.getNegative(negatives);
		neutral = method2.getNeutral(neutral);
		positives = method2.getPositive(positives);
	}
}
	
	
