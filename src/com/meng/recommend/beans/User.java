package com.meng.recommend.beans;

import java.util.List;

public class User {

    public String id;
    public List<Topic> listens;
    
    public User(String id) {
        this.id = id;
    }
}
