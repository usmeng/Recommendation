package com.meng.recommend.test;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.meng.recommend.beans.ListenItem;
import com.meng.recommend.beans.Topic;
import com.meng.recommend.service.IRecommend;
import com.meng.recommend.service.RecommendService;

public class IRecommendTest {

	@Test
	public void testGetRecommendation() {
		IRecommend test = RecommendService.getInstance();
		List<String> list = test.getRecommendation("57d808b351224d0300c000df", 4);
		
		TestCase.assertEquals(4, list.size());
	}

	@Test
	public void testAddTopic() {
		RecommendService test = RecommendService.getInstance();
		int size = test.getTopics().size();

		Topic topic = new Topic();
		topic.id = "57d808b654189d0300c568rf";
		topic.description = "test descripition";
		topic.name = "test case";
		test.addTopic(topic);
		
		TestCase.assertEquals(size + 1, test.getTopics().size());
	}

	@Test
	public void testAddListenItem() {
		RecommendService test = RecommendService.getInstance();
		int size = test.getListenItems().size();
		
		test.addListenItem(new ListenItem());
		
		TestCase.assertEquals(size + 1, test.getListenItems().size());
	}

}
