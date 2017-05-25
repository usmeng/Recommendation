package com.meng.recommend.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.meng.recommend.utils.DateUtils;

public class DateUtilsTest {

	@Test
	public void testConvert() {
		long convert = DateUtils.convert("2016-04-01T20:37:20.142000");
		
		String date = String.valueOf(convert);
		TestCase.assertEquals(13, date.length());
	}

}
