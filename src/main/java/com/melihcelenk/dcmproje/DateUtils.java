package com.melihcelenk.dcmproje;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String getTodayDate(){
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HHmmss.SSS");
        LocalDateTime now = LocalDateTime.now();
        String dateToday = dtfDate.format(now);
        return dateToday;
    }
    public static String getTodayTime(){
        DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HHmmss.SSS");
        LocalDateTime now = LocalDateTime.now();
        String timeToday = dtfTime.format(now);
        return timeToday;
    }


}
