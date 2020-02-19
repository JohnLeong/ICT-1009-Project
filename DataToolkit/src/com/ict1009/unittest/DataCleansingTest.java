package com.ict1009.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ict1009.utilities.DataCleansing;

public class DataCleansingTest {
	
	@Test
	public void testDataCleanse() {
		String testString;
		
		testString = DataCleansing.dataCleanse("Hello               world");
		assertTrue(testString.equals("Hello world"));
		
		testString = DataCleansing.dataCleanse("          Hello               world");
		assertTrue(testString.equals(" Hello world"));
		
		testString = DataCleansing.dataCleanse("      Hello world");
		assertTrue(testString.equals(" Hello world"));
		
		testString = DataCleansing.dataCleanse("H e l l o w o r l     d");
		assertTrue(testString.equals("H e l l o w o r l d"));
	}
}
