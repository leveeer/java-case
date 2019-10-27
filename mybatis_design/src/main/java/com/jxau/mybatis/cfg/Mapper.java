package com.jxau.mybatis.cfg;

/**
 * 用于封装执行的sql语句和结果类型的全限定类名
 */
public class Mapper {

    private String queryString;  //SQL语句
    private String resultType;   //实体类的全限定类名

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        System.out.println(resultType);
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
