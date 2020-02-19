package com.ict1009.unittest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ict1009.utilities.StringConverter;

public class StringConverterTest {

	@Test
	public void testConvertUnicode() {
		assertTrue(StringConverter.convertUnicodeToUTF8("Hello world").equals("Hello world"));
		assertTrue(StringConverter.convertUnicodeToUTF8("ABCDEFG").equals("ABCDEFG"));
	}
}
