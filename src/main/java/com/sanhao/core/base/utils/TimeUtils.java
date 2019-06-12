package com.sanhao.core.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATETIME = "yyyy-MM-dd";

    public static String getDateTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dt = dateFormat.format(date);
        return dt;
    }

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME);
        String dt = dateFormat.format(date);
        return dt;
    }

    /*
     * 将时间转换为时间戳
     */
    public static Long timeToTimeStamp(String time) throws ParseException {
        String date = getDate();
        String startTime = date + " 00:00:00";
        String endTime = date + " " + time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startTimeDate = simpleDateFormat.parse(startTime);
        Date endTimeDate = simpleDateFormat.parse(endTime);
        long startTimeLong = startTimeDate.getTime() / 1000;
        long tiendTimeLong = endTimeDate.getTime() / 1000;
        return tiendTimeLong - startTimeLong;
    }

    public static String timeStampToDate(String time) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_FORMAT);
        long lt = new Long(time);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 时间相减
     *
     * @param strDateBegin
     * @param strDateEnd
     * @param iType
     * @return
     */
    public static Long getDiffDate(String strDateBegin, String strDateEnd, int iType) {
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(parseDate(strDateBegin, DATETIME_FORMAT));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(parseDate(strDateEnd, DATETIME_FORMAT));
        long lBegin = calBegin.getTimeInMillis();
        long lEnd = calEnd.getTimeInMillis();
        if (iType == Calendar.MILLISECOND)
            return (Long) ((lEnd - lBegin) / 1L);
        if (iType == Calendar.SECOND)
            return (Long) ((lEnd - lBegin) / 1000L);
        if (iType == Calendar.MINUTE)
            return (Long) ((lEnd - lBegin) / 60000L);
        if (iType == Calendar.HOUR)
            return (Long) ((lEnd - lBegin) / 3600000L);
        if (iType == Calendar.DAY_OF_MONTH) {
            return (Long) ((lEnd - lBegin) / 86400000L);
        }
        return -1L;
    }

    /**
     * 格式化字符串为日期
     *
     * @param date
     * @param format
     * @return
     */
    public static Date parseDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String checkOption(String option, String _date, Integer minute, Integer calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
        Calendar cl = Calendar.getInstance();
        Date date = null;

        try {
            date = (Date) sdf.parse(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cl.setTime(date);
        if ("pre".equals(option)) {
            cl.add(calendar, -1 * minute);

        } else if ("next".equals(option)) {
            cl.add(calendar, minute);
        } else {
            // do nothing
        }
        date = cl.getTime();
        return sdf.format(date);
    }

    /**
     * 根据指定时间格式"yyyy-MM-dd HH:mm:ss"，将日期转换为字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
        String dateString = dateFormat.format(date);
        return dateString;
    }

    /**
     * 根据指定的时间格式"yyyy-MM-dd HH:mm:ss"，将字符串转换为日期
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_FORMAT);
        return dateFormat.parse(dateString);
    }

    /*
     * 将日期时间转换为时间戳
     */
    public static Long getTimeStamp(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(time);
        long worldStamp = date.getTime();
        return worldStamp;
    }

}
