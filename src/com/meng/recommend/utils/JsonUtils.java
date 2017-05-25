package com.meng.recommend.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meng.recommend.beans.ListenItem;
import com.meng.recommend.beans.Topic;

public class JsonUtils {

    public static Map<String, Topic> parseTopic() {
        File file = new File("../j2ee-workspace/SimpleHabit/asserts/subtopics.json");
        Map<String, Topic> res = new HashMap<String, Topic>();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file)); 
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<Topic>>() {}.getType();
            List<Topic> topics = gson.fromJson(reader, collectionType);
            
            for (Topic topic : topics) {
                res.putIfAbsent(topic.id, topic);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return res;
    }

    public static List<ListenItem> parseListenItem() {
        File file = new File("../j2ee-workspace/SimpleHabit/asserts/listens.json");
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file)); 
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<ListenItem>>() {}.getType();
            List<ListenItem> listenItems = gson.fromJson(reader, collectionType);
            for (ListenItem item : listenItems) {
                item.date = DateUtils.convert(item.listenDate);
            }
            return listenItems;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        
        return null;
    }
    
    public static <T> List<T> parseJsonFile(String path, T t) {
        File file = new File(path);
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file)); 
            Gson gson = new Gson();
            Type collectionType = new TypeToken<List<T>>() {}.getType();
            return gson.fromJson(reader, collectionType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}
