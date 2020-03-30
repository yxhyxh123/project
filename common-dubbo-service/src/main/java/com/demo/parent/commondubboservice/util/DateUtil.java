package com.demo.parent.commondubboservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * projectName demo
 * className DateUtil
 * description 日期工具类
 *
 * @author yzh
 * @date 2020/3/25 4:45 下午
 */
public class DateUtil {

    /**
     * 按照指定的格式，将日期类型对象转换成字符串，例如：yyyy-MM-dd,yyyy/MM/dd,yyyy/MM/dd hh:mm:ss
     * 如果传入的日期为null,则返回空值
     *
     * @param date
     *            日期类型对象
     * @param format
     *            需转换的格式
     * @return
     */
    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formater = new SimpleDateFormat(format);
        return formater.format(date);
    }

    /**
     * 按照指定的格式，将字符串解析成日期类型对象，例如：yyyy-MM-dd,yyyy/MM/dd,yyyy/MM/dd hh:mm:ss
     *
     * @param dateStr
     *            日期格式的字符串
     * @param format
     *            字符串的格式
     * @return 日期类型对象
     */
    public static Date parseDate(String dateStr, String format) throws ParseException {
        if ("".equalsIgnoreCase(dateStr)) {
            return null;
        }
        // 转换年月日格式
        dateStr = dateStr.replaceAll("年", "-").replaceAll("月", "-").replaceAll("日", "").replaceAll("/", "-");
        SimpleDateFormat formater = new SimpleDateFormat(format);

        return formater.parse(dateStr);
    }


   /* public static Date timeStampToDate(Long timeStamp,String format){
        return
    }*/
}
