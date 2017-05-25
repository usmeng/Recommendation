package com.meng.recommend.service;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.meng.recommend.beans.ListenItem;
import com.meng.recommend.beans.Topic;
import com.meng.recommend.utils.JsonUtils;

/***
 * Recommend Service
 * @author meng
 */
public class RecommendService implements IRecommend {

    private static RecommendService instance;
    
    private Map<String, Topic> mTopics;
    private List<ListenItem> listenItems;
    
    // User -> Topic List
    private Map<String, List<String>> userTopicMaps;
    // Topic -> User List
    private Map<String, List<String>> topicUserMaps;
    
    private RecommendService(){}
    
    public static synchronized RecommendService getInstance() {
        if (instance == null) {
            instance = new RecommendService();
        }
        instance.init();
        return instance;
    }
    
    private void init() {
        // parse subtopics.json to mTopics Map<String, Topic> mTopics
        mTopics = JsonUtils.parseTopic();
        // parse listens.json to List<ListenItem> listenItems
        listenItems = JsonUtils.parseListenItem();
        
        // sort listenItems by listen date
        Collections.sort(listenItems, new Comparator<ListenItem>() {
            public int compare(ListenItem o1, ListenItem o2) {
                return (o1.date - o2.date) > 0 ? 1 : -1;
            }
        });
        
        userTopicMaps = new HashMap<String, List<String>>();
        topicUserMaps = new HashMap<String, List<String>>();
        
        for (ListenItem item : listenItems) {
            mapListenItems(item);
        }
        
        System.out.println("total number of topics: " + mTopics.size());
        System.out.println("total number of listenItems: " + listenItems.size());
        System.out.println("total number of users: " + userTopicMaps.size());
        System.out.println("total number of topics user listened: " + topicUserMaps.size());
    }

    /***
     * map ListenItem to 2 maps.
     * map userTopicMaps: key is user, value is topics the user listen.
     * map topicUserMaps: key is topic, value is users who listen the topic
     * @param item
     */
    private void mapListenItems(ListenItem item) {
        List<String> list = userTopicMaps.getOrDefault(item.user, new LinkedList<String>());
        list.add(item.subtopic);
        userTopicMaps.put(item.user, list);
        
        List<String> list2 = topicUserMaps.getOrDefault(item.subtopic, new LinkedList<String>());
        list2.add(item.user);
        topicUserMaps.put(item.subtopic, list2);
    }


    /***
     * @return is recommended topics
     * @param topicId is the target topic
     *        number is number of recommendation topics 
     */
    public List<String> getRecommendation(String topicId, int number) {
    	List<String> res = new LinkedList<String>();
    	if(topicId == null || topicId.length() == 0) return res;
        if(!topicUserMaps.containsKey(topicId)) return res;
        
        // get users who listened the topic
        List<String> users = topicUserMaps.get(topicId);
        
        // the map use to record how many times of the topic listened by all users
        Map<String, Integer> maps = new HashMap<String, Integer>();
        
        for (String user : users) {
            List<String> topics = userTopicMaps.get(user);
            for (String string : topics) {
            	if(topicId.equals(string)) continue;
                maps.put(string, maps.getOrDefault(string, 0) + 1);
            }
        }
        
        // sort topics by numbers
        PriorityQueue<Item> queue = new PriorityQueue<Item>(number, new Comparator<Item>() {
            
            public int compare(Item o1, Item o2) {
                return o1.times - o2.times;
            }
        });
        
        // find number required topics
        Iterator<String> it = maps.keySet().iterator();
        while(it.hasNext()) {
            String next = it.next();
            Integer times = maps.get(next);
            if(queue.size() < number) {
                queue.add(new Item(next, times));
            } else if(queue.peek().times <= times){
                // sorted by two factor, first priority is times of listened, second is listened date
                queue.poll();
                queue.add(new Item(next, times));
            }
        }
        
        for (Item item : queue) {
            res.add(item.topicId);
        }
        
        return res;
    }
    
    class Item {
        public String topicId;
        public int times;
        
        public Item(String topicId, int times) {
            this.topicId = topicId;
            this.times = times;
        }
    }

    public void addTopic(Topic topic) {
    	if(topic == null) return;
        mTopics.putIfAbsent(topic.id, topic);
    }

    /**
     * add new listenitem and map it to collections.
     */
    public void addListenItem(ListenItem item) {
        listenItems.add(item);
        mapListenItems(item);
    }
    
    public Map<String, Topic> getTopics() {
    	return mTopics;
    }
    
    public List<ListenItem> getListenItems() {
    	return listenItems;
    }
}
