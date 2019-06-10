package com.hzqing.admin.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hzqing
 * @date 2019-06-10 08:54
 */
public class DateUtils {
    private static final SimpleDateFormat yearAandMonth = new SimpleDateFormat("yyyyMM");

    /**
     *
     * @return yyyyMM
     */
    public  static  String getYearAndMonth(){
        return yearAandMonth.format(new Date());
    }

    /**
     *
     * @param date
     * @return yyyyMM
     */
    public  static  String getYearAndMonth(Date date){
        return yearAandMonth.format(date);
    }

}
