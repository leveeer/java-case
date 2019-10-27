package com.jxau.mybatis.cfg;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义mybatis的配置类
 */
public class Configuration {

    private String driver;
    private String url;
    private String username;
    private String password;
    private Map<String, Mapper> mappers = new HashMap<String, Mapper>();

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        //将新传入的mappers追加到当前的mappers中
        //不能使用this.mappers = mappers;这样的话新传入的mappers将覆盖当前的mappers,map集合中将永远只有一个entry
        this.mappers.putAll(mappers);
    }
}
