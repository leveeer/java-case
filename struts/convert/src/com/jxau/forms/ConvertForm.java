package com.jxau.forms;

import org.apache.struts.action.ActionForm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xie
 */
public class ConvertForm extends ActionForm{

	private int intValue;
	private double doubleValue;
	//private java.util.Date utilDate;
	private java.sql.Date sqlDate;
    private String utilDate;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

   /* public Date getUtilDate() {
        return utilDate;
    }

    public void setUtilDate(Date utilDate) {
        this.utilDate = utilDate;
    }
    */

   /*第六种方式
   * 在表单中将utilDate定义为String
   * 将getUtilDate方法的返回值定义为Date类型
   * 在getUtilDate方法中对日期进行转换
   * 结论：该方法不可行，jsp页面取值出现错误
   * */
    /*public Date getUtilDate() {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        try {
            date = sdf.parse(utilDate);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }*/

    public String getUtilDate() {
        return utilDate;
    }

    public void setUtilDate(String utilDate) {
        this.utilDate = utilDate;
    }

    public java.sql.Date getSqlDate() {
        return sqlDate;
    }

    public void setSqlDate(java.sql.Date sqlDate) {
        this.sqlDate = sqlDate;
    }

    @Override
    public String toString() {
        return "ConvertForm{" + "intValue=" + intValue + ", doubleValue=" + doubleValue + ", utilDate=" + utilDate + ", sqlDate=" + sqlDate + '}';
    }
}
