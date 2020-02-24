package com.ict1009.stanfordcorenlp;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.List;

import org.json.JSONObject;

import com.ict1009.utilities.JSONUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

abstract public class SentimentAnalyzer {

	protected static StanfordCoreNLP stanfordCoreNLP;
	
	
	public SentimentAnalyzer(String modes) {
		stanfordCoreNLP = Pipeline.getPipeline(modes);
	}
	public SentimentAnalyzer() {
		this("tokenize, ssplit, pos, lemma, ner, parse, sentiment");
	}
	
	static {
		stanfordCoreNLP = Pipeline.getPipeline("tokenize, ssplit, pos, lemma, ner, parse, sentiment");
	}
		
	/**
	 * For getting all comments from JSONOjbect
	 * @param jsonObject		
	 * @return
	 */
	abstract protected ArrayList<String> parseJSONComments(JSONObject jsonObject);
	
	protected String readJSONFileToString(String path) throws IOException {
		return JSONUtility.parseJSONToString(path);
	}
	
	/**
	 * Uses StanfordCoreNLP models to conduct sentiment analysis to 
	 * determine user's feelings/reaction from his comment of a particular post.
	 * 
	 * @param comment		Comment to analyze
	 * @return				Category of user's feelings (Neutral/Postiive/Negative/Very Negative)
	 */
	public static String commentCategory(String comment) {
		
		CoreDocument coreDocument = new CoreDocument(comment);
		stanfordCoreNLP.annotate(coreDocument);
		
		HashMap<String, Integer> reaction  = new HashMap<String, Integer>();				
		List<CoreSentence> sentences = coreDocument.sentences();		
		int count; String sentimentCategory;
		
		for (CoreSentence sentence : sentences) {
			sentimentCategory = sentence.sentiment();
			count = reaction.containsKey(sentimentCategory) ? reaction.get(sentimentCategory) : 0;
			reaction.put(sentimentCategory, count + 1);
		}
	
//		try { 
			return Collections.max(reaction.entrySet(), Comparator.comparingInt(HashMap.Entry::getValue)).getKey();
//		} catch (Exception e) {
//			return "Neutral";
//		}
		
	}

	/**
	 * Evaluate sentiment results based on the list of all comments.
	 * @param sentences		ArrayList containing everyone's comments.
	 * @return				HashMap containing user feelings with the number of counts.
	 */
	protected HashMap<String, Integer> getSentimentResults(List<String> sentences) {
		HashMap<String, Integer> reactions = new HashMap<String, Integer>();
		int mapKeyCount; String reaction;
		
//		sentences.removeAll((new ArrayList<String>()).add("None"));
		for (int i = 0; i < sentences.size(); ++i) {
			System.out.println("Evaluating Sentiment " +  (i+1));
			System.out.println(sentences.get(i));
			reaction = commentCategory(sentences.get(i));
			mapKeyCount = reactions.containsKey(reaction) ? reactions.get(reaction) : 0;
			reactions.put(reaction, mapKeyCount + 1);
		}
		return reactions;
	}
	
}

