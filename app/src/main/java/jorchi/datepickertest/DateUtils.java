package jorchi.datepickertest;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yml on 16/6/3.
 */
public class DateUtils {


    /**
     * 获取现在时间
     * @return
     */
    public static Date getCurrentDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getCurrentDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getStringDateShort1() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong2(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss  *   * @param dateDate  * @return
     */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss  *   * @param dateDate  * @return
     */
    public static String dateToStrLong2(long timestamp) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String dateString = formatter.format(new Date(timestamp));
        return dateString;
    }
    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss  *   * @param dateDate  * @return
     */
    public static String dateToStrLong3(long timestamp) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(new Date(timestamp));
        return dateString;
    }
    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 获取当前年份
     * @return
     */
    public static int getCurrentYear(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     * @return
     */
    public static int getCurrentMonth(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH)+1;
    }

    /**
     * 获取当前日
     * @return
     */
    public static int getCurrentDay(){
        Calendar c = Calendar.getInstance();

        return c.get(Calendar.DAY_OF_MONTH);
    }
    /**
     * 得到现在小时
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    //得到现在的分钟
    public static String getMinutes(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String minutes;
        minutes = dateString.substring(14, 16);
        return minutes;
    }

    public static ArrayList<String> getYeasList(){
        ArrayList<String> dateList=new ArrayList<String>();
        int curYear=getCurrentYear();
        for(int i=1970;i<=curYear+1;i++){
            dateList.add(i+"");
        }
        return dateList;
    }


    /**
     * 根据年 月 获取对应的月份 天数
     * */
    public static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     *获取年对应的月份
     * 以当前日期为结点
     *@Author: yumeili
     *@Since: 2015-12-23下午5:24:53
     */
    public static ArrayList<String> getMonthList(String yearKey,boolean isAll){
        ArrayList<String> monthlist=new ArrayList<String>();
        int currentYear=getCurrentYear();
        int currentMonth=getCurrentMonth();
        int monthSize=1;
        if(StringUtils.isEmpty(yearKey)){
            yearKey=currentYear+"";
        }
//        if ( Integer.parseInt (yearKey)==currentYear && isAll) {
//            monthSize=currentMonth;
//        } else {
            monthSize=12;
//        }
        for ( int i=1;i<=monthSize;i++) {
            if(i<=9){
                monthlist.add ("0"+i);
            }else{
                monthlist.add (i+"");
            }
        }
        return monthlist;
    }

    /**
     *获取月份对应的日
     * 以当前日期为结点
     *@Author: yumeili
     *@Since: 2015-12-23下午5:24:53
     */
    public static ArrayList<String> getDayList(String yearKey,String monthKey){
        ArrayList<String> daylist=new ArrayList<String>();
        int currentYear=getCurrentYear();
        int currentMonth=getCurrentMonth();
        int currentDay=getCurrentDay();
        int daySize=1;
        if(StringUtils.isEmpty(yearKey)){
            yearKey=currentYear+"";
        }
        if(StringUtils.isEmpty(monthKey)){
            monthKey=currentMonth+"";
        }
//        if(Integer.parseInt (monthKey)==currentMonth && Integer.parseInt (yearKey)==currentYear){
//            daySize=currentDay;
//        }else{
            daySize=getDaysByYearMonth(Integer.parseInt(yearKey),Integer.parseInt(monthKey));
//        }
        for ( int i=1;i<=daySize;i++) {
            if(i<=9){
                daylist.add ("0"+i);
            }else{
                daylist.add (i+"");
            }

        }
        return daylist;
    }

    public static ArrayList<String> getHourList(){
        ArrayList<String> hourlist = new ArrayList<String>();

        for(int i=1;i<=24;i++){
            if(i<=9){
                hourlist.add("0"+i);
            }else{
                hourlist.add(i+"");
            }
        }

        return hourlist;
    }

    public static ArrayList<String> getMinuteList(){
        ArrayList<String> minutelist = new ArrayList<String>();

        for(int i=0;i<60;i++){
            if(i<=9){
                minutelist.add("0"+i);
            }else{
                minutelist.add(i+"");
            }

        }
        return  minutelist;
    }

}