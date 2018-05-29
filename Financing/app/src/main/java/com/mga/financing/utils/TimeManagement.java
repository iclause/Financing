package com.mga.financing.utils;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ʱ��ת��������
 *
 * @author max.chengdu 2015��11��29��
 */
public class TimeManagement {


    /*日期和时间模式
    结果
    "yyyy.MM.dd G 'at' HH:mm:ss z"           2001.07.04 AD at 12:08:56 PDT
    "EEE, MMM d, ''yy"                       Wed, Jul 4, '01
    "h:mm a"                                 12:08 PM
    "hh 'o''clock' a, zzzz"                  12 o'clock PM, Pacific Daylight Time
    "K:mm a, z"                              0:08 PM, PDT
    "yyyyy.MMMMM.dd GGG hh:mm aaa"           02001.July.04 AD 12:08 PM
    "EEE, d MMM yyyy HH:mm:ss Z"             Wed, 4 Jul 2001 12:08:56 -0700
    "yyMMddHHmmssZ"                          010704120856-0700
    "yyyy-MM-dd'T'HH:mm:ss.SSSZ"             2001-07-04T12:08:56.235-0700
    "yyyy-MM-dd'T'HH:mm:ss'Z'"                "2017-09-05T10:54:39Z"
    */
    public static String TAG = TimeManagement.class.getSimpleName();

    public static String longToStringDate(Long l) {
        String time = null;
        SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
        if (l != null) {
            Date date = new Date(l);
            time = sfd.format(date);
            return time;
        }
        return time;
    }


    public static Long stringToLongDate(String s) {
        Long time = null;
        try {
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sfd.parse(s);
            time = date.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;

    }

    public static long stringToLongDate(String s, SimpleDateFormat sfd) {
        Long time = null;
        try {

            Date date = sfd.parse(s);
            Log.d(TAG, "stringTolongdate=" + date);
            time = date.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;

    }

    public static String exchangeStringDate(String date) throws ParseException {
        if (date != null && date.length() > 10) {
            String result = date.substring(0, 10);
            return result;
        } else {
            return null;
        }

    }

    /**
     * ����HH:mm:ss
     *
     * @param ����ΪString����
     * @return ����HH:mm:ss
     * @throws ParseException
     */
    public static String exchangeStringTime(String date) throws ParseException {
        if (date != null && date.length() > 10) {
            String result = date.substring(10, date.length());
            return result;
        } else {
            return null;
        }
    }


    public static int compare(Long o1, Long o2) {
        if (o1 > o2) {
            return 1;
        } else if (o1 < o2) {
            return -1;
        } else {
            return 0;
        }
    }
}
