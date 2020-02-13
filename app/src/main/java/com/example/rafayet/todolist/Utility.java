package com.example.rafayet.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    public static String getDateFromInt(int openingDate) {
        long diff = (openingDate - 693595);
        long totalDiff = (86400000 * diff);
        SimpleDateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
        String dateText = null;
        try {
            Date d = dfDate.parse("01/01/1900");
            long initialDayLong = d.getTime();
            long last = totalDiff + initialDayLong;
            Date date = new Date(last);
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
            dateText = df2.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateText;
    }

    public static int dateToInt(String date) {
        SimpleDateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        Date d1 = null;
        try {
            d = dfDate.parse("01/01/1900");
            d1 = dfDate.parse(date);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        long diff = d1.getTime() - d.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        diffDays = diffDays + 693595;
        return (int) diffDays;
    }
}
