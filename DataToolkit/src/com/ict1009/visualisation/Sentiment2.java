package com.ict1009.visualisation;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Sentiment2 {
	static double positives;
	static double negatives;
	static double neutral ;


public void StanfordAnallysis(String description) {
		
	    Properties props = new Properties();	
	    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, sentiment");
	 	StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	 	
		CoreDocument coreDocument = new CoreDocument(description);
		pipeline.annotate(coreDocument);
				
	 	List<CoreSentence> sentences = coreDocument.sentences();
	 	
		for(CoreSentence sentence: sentences) {
				
			String sentiment = sentence.sentiment();
			if (sentiment.equals("Positive")) {
				positives++;
			}
			else if(sentiment.equals("Negative")) {
				negatives++;
			}
			else {
				System.out.println("neutral");	
				neutral++;
			}

			
			
			}
			
		}
		public double getPositive(double pos){
			pos= positives;
			return pos;
		}
		public double getNegative(double neg){
			neg= negatives;
			return neg;
		}
		public double getNeutral(double neu){
			neu= neutral;
			return neu;
		}
}


