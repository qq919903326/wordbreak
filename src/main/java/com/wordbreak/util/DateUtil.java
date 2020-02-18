package com.wordbreak.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String getDate(){
        Calendar cal=Calendar.getInstance();//使用日历类
        int year=cal.get(Calendar.YEAR);//得到年
        int month=cal.get(Calendar.MONTH)+1;//得到月，因为从0开始的，所以要加1
        int day=cal.get(Calendar.DAY_OF_MONTH);//得到天
        return year+"-"+month+"-"+day;
    }

    public static boolean isBelong(){

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date now =null;
        Date beginTime = null;
        Date endTime = null;
        try {
            now = df.parse(df.format(new Date()));
            beginTime = df.parse("09:00");
            endTime = df.parse("19:00");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return belongCalendar(now, beginTime, endTime);
    }

    public static boolean isBelong2(){

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        Date now =null;
        Date beginTime = null;
        Date endTime = null;
        try {
            now = df.parse(df.format(new Date()));
            beginTime = df.parse("08:00");
            endTime = df.parse("19:00");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return belongCalendar(now, beginTime, endTime);
    }


    /**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatStringDate(String timeStamp){
        Long l = new Long(timeStamp);
        String sd = format.format(new Date(l));
        return sd;
    }

    public static void main(String[] args) {
        System.out.println(new Date(1538030790038l));
    }
}
