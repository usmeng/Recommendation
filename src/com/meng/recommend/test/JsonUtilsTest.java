package com.meng.recommend.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.meng.recommend.utils.JsonUtils;

public class JsonUtilsTest {

	@Test
	public void testParseTopic() {
		TestCase.assertTrue(JsonUtils.parseTopic().size() > 0);
	}

	@Test
	public void testParseListenItem() {
		TestCase.assertTrue(JsonUtils.parseListenItem().size() > 0);
	}

}
