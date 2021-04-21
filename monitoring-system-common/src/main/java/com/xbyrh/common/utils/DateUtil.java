package com.xbyrh.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create at 2021/4/17
 *
 * @author chenxinhui
 */
public class DateUtil {

    private DateUtil(){}


    public static String convertToString(Date date) {

        return " ";

    }

    public static Date stringToDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return simpleDateFormat.parse(date);
        }catch (Exception e){
            throw new IllegalArgumentException("日期格式不正确");
        }
    }

    public static String dateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return simpleDateFormat.format(date);
        }catch (Exception e){
            throw new IllegalArgumentException("日期格式不正确");
        }
    }

    public static void main(String[] args) {
        System.out.println(stringToDate("2020-12-12 01:01:01"));
        System.out.println(dateToString(new Date()));
    }

}
