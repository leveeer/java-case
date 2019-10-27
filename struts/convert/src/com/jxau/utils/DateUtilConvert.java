package com.jxau.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

/**
 * @author xie
 */
public class DateUtilConvert implements Converter {

	@Override
    public Object convert(Class type, Object value) {
        System.out.println(value);
        if (value == null){
            return value;
        }
        if (value instanceof  Date){
            return value;
        }
        Date date = null;
        if (value instanceof String) {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

            try {
                date = sdf.parse((String) value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
            return date;
	}

}
