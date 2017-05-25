package com.meng.recommend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static long convert(String dateString) {
        if(dateString == null) return 0;
        if(dateString.length() != 26) {
            dateString += ".000000";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        try {
            Date date = format.parse(dateString);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
    
//    // 2016-09-02T02:11:41.369000
    public static void main(String[] args) {
        System.out.println(convert("2016-04-01T20:37:20.142000"));
    }
}
