package com.meng.recommend.service;


import java.util.List;

import com.meng.recommend.beans.ListenItem;
import com.meng.recommend.beans.Topic;

public interface IRecommend {

    public List<String> getRecommendation(String topicId, int number);
    
    public void addTopic(Topic topic);
    
    public void addListenItem(ListenItem item);
}
