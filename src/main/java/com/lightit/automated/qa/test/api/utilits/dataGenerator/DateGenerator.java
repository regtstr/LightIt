package com.lightit.automated.qa.test.api.utilits.dataGenerator;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateGenerator {

    /**
     * Generate random time
     * @return time in format hh:mm
     */
    public static String getRandomTime() {
        long beginTime = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
        long endTime = Timestamp.valueOf("2013-12-31 00:58:00").getTime();

        long diff = endTime - beginTime + 1;

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");

        Date randomDate = new Date(beginTime + (long) (Math.random() * diff));

        return dateFormat.format(randomDate);
    }

    /**
     * Generate random time
     * @return time in format hh:mm
     */
//    public static String getRandomTimeByFormat(TimeFormat timeFormat) {
//        long beginTime = Timestamp.valueOf("2013-01-01 00:00:00").getTime();
//        long endTime = Timestamp.valueOf("2013-12-31 00:58:00").getTime();
//
//        long diff = endTime - beginTime + 1;
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat(timeFormat.getTimeFormat());
//
//        Date randomDate = new Date(beginTime + (long) (Math.random() * diff));
//
//        return dateFormat.format(randomDate);
//
//    }

    public static String getFutureDate(int addDays) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, addDays);

        Date currentDatePlusOne = c.getTime();
        System.out.println(dateFormat.format(currentDatePlusOne));

        return dateFormat.format(currentDatePlusOne);
    }

    public static String getFutureDate(int addDays, String neededDateFormat) {
        DateFormat dateFormat = new SimpleDateFormat(neededDateFormat);
        Date currentDate = new Date();
//        System.out.println(dateFormat.format(currentDate));

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, addDays);

        Date currentDatePlusOne = c.getTime();
//        System.out.println(dateFormat.format(currentDatePlusOne));

        return dateFormat.format(currentDatePlusOne);
    }

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));
        return dateFormat.format(currentDate);
    }

    public static String getCurrentDate(String neededDateFormat) {
        DateFormat dateFormat = new SimpleDateFormat(neededDateFormat);
        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));
        return dateFormat.format(currentDate);
    }

    public static String getPastDate(int minusDays) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, -1* minusDays);

        Date currentDatePlusOne = c.getTime();
        System.out.println(dateFormat.format(currentDatePlusOne));

        return dateFormat.format(currentDatePlusOne);
    }

    public static String getPastDate(int minusDays, String neededDateFormat) {
        DateFormat dateFormat = new SimpleDateFormat(neededDateFormat);
        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, -1* minusDays);

        Date currentDatePlusOne = c.getTime();
        System.out.println(dateFormat.format(currentDatePlusOne));

        return dateFormat.format(currentDatePlusOne);
    }
}
