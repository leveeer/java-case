package com.jxau.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName Convert
 * @Description 第七种方式：自定义jstl日期转换函数
 * @Author xie
 * @Date 2019/8/16 15:46
 * @Version 1.0
 */
public class Convert {
    public static Date convert(String utilDate){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(utilDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
