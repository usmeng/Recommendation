package com.meng.recommend.beans;

import java.util.LinkedList;
import java.util.List;

public class DataResponse {

	public int status;
	public String searchedTopic;
	public String msg;
	public List<Subtopic> recommendations;

    public static class Subtopic {
        public String subtopic;
        
        public Subtopic(String subtopic) {
        	this.subtopic = subtopic;
        }
    }
    
    public DataResponse(){
    	recommendations = new LinkedList<>();
    }
}
