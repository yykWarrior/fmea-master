package com.rb.fmea.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
    /**
     * 格式化日期
     *
     * @param dateStr 字符型日期
     * @param format 格式
     * @return 返回日期
     */
    public static Date parseDate(String dateStr, String format)
    {
        Date date = null;
        try
        {
            java.text.DateFormat df = new SimpleDateFormat(format);
            date = (Date)df.parse(dateStr);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Date currentTime_2 = null;
        try {
            currentTime_2 = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return currentTime_2;
    }





    /**
     * @param dateStr，默认格式为：yyyy/MM/dd
     * @return Date
     */
    public static Date parseDate(String dateStr)
    {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @Author yyk
     * @Description //TODO 格式化日期
     * @Date 2020/3/28 10:21
     * @Param [dateStr]
     * @return java.util.Date
     **/
    public static String parseTime(Date date)
    {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 格式化输出日期
     *
     * @param date 日期
     * @param format 格式
     * @return 返回字符型日期
     */
    public static String formatDate(Date date, String format)
    {
        String result = "";
        try
        {
            if(date != null)
            {
                java.text.DateFormat df = new SimpleDateFormat(format);
                result = df.format(date);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 格式化日期，默认格式：yyyy/MM/dd
     *
     * @param date 日期
     * @return 返回字符型日期
     */
    public static String format(Date date)
    {
        return formatDate(date, "yyyy/MM/dd");
    }





    /**
     * 返回年份
     *
     * @param date 日期
     * @return 返回年份
     */
    public static int getYear(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回月份
     *
     * @param date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日份
     *
     * @param date 日期
     * @return 返回日份
     */
    public static int getDay(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //System.out.println(calendar.getTimeInMillis());
        return calendar.getTimeInMillis();
    }

    /**
     * 返回字符型日期
     *
     * @param date 日期
     * @return 返回字符型日期
     */
    public static String getDate(Date date)
    {
        return formatDate(date, "yyyy/MM/dd");
    }

    /**
     * 返回字符型时间，格式默认为：HH:mm:ss
     *
     * @param date 日期
     * @return 返回字符型时间
     */
    public static String getTime(Date date)
    {
        return formatDate(date, "HH:mm:ss");
    }

    /**
     * 返回字符型日期时间，格式默认为：yyyy/MM/dd HH:mm:ss
     *
     * @param date 日期
     * @return 返回字符型日期时间
     */
    public static String getDateAndTime(Date date)
    {
        return formatDate(date, "yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 日期相加
     *
     * @param date 日期
     * @param day 天数
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int day)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getMillis(date) + ((long)day) * 24 * 3600 * 1000);
        return calendar.getTime();
    }


    public static Date addDateByMon(Date date,int mon){
        int oldMonth = getMonth(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH,oldMonth+mon-1);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date date = addDateByMon(new Date(), 3);
        System.out.println(DateUtil.parseTime(new Date()));
        System.out.println(DateUtil.parseTime(date));

    }


    /**
     * 日期相减，返回相隔天数
     *
     * @param date 日期
     * @param date1 日期
     * @return 返回相减后的天数
     */
    public static int diffDate(Date date, Date date1)
    {
        int n=0;
        if(date==null||date1==null){
            n=0;
        }else {
            n = (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
        }
        return n;
    }

    /**
     * 取得指定月份的第一天
     *
     * @param strdate String
     * @return String
     */
    public static String getMonthBegin(String strdate)
    {
        Date date = parseDate(strdate);
        return formatDate(date, "yyyy/MM") + "/01";
    }

    /**
     * 取得指定月份的最后一天
     *
     * @param strdate String
     * @return String
     */
    public static String getMonthEnd(String strdate)
    {
        Date date = parseDate(getMonthBegin(strdate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return format(calendar.getTime());
    }

    /**
     * 得到当前年份
     * @return
     */
    public static int getCurrYear()
    {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 得到一年的总周数
     *
     * @param year
     * @return
     */
    public static int getWeekCountInYear(int year)
    {
        Calendar c = new java.util.GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekOfYear(c.getTime());
    }

    /**
     * 取得当前日期是多少周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date)
    {
        Calendar c = new java.util.GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week)
    {
        Calendar c = new java.util.GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (java.util.GregorianCalendar)c.clone();
        cal.add(Calendar.DATE, week * 7);
        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week)
    {
        Calendar c = new java.util.GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);
        Calendar cal = (java.util.GregorianCalendar)c.clone();
        cal.add(Calendar.DATE, week * 7);
        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 得到某年某月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirestDayOfMonth(int year, int month)
    {
        month = month - 1;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        int day = c.getActualMinimum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    /**
     * 提到某年某月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(int year, int month)
    {
        month = month - 1;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        int day = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return 所在周的第一天的日期
     */
    public static Date getFirstDayOfWeek(Date date)
    {
        Calendar c = new java.util.GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return 所在周最后一天的日期
     */
    public static Date getLastDayOfWeek(Date date)
    {
        Calendar c = new java.util.GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return c.getTime();
    }

    //比较两个时间d1<d2返回-1，d1==d2返回0，d1>d2返回1
    public static int compaTime(Date d1, Date d2){
      return   d1.compareTo(d2);
    }



    public static Date[] StringToDate(String str) throws ParseException {
        Date dateArray[]= new Date[2];
        if(StringUtils.isBlank(str)){
            dateArray[0]=null;
            dateArray[1]=null;
            return dateArray;
        }
        String dates[]=str.split(" ");
        String startDate=dates[0]+" 00:00:00";
        String endDate=dates[0]+" 23:59:59";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = simpleDateFormat.parse(startDate);
        Date date2 = simpleDateFormat.parse(endDate);
        dateArray[0]=date1;
        dateArray[1]=date2;
        return dateArray;
    }



    /**
     * 字符串转为Date
     * @return
     */
    public static Date StringToDate1(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = simpleDateFormat.parse(str);
        return date1;
    }





}
