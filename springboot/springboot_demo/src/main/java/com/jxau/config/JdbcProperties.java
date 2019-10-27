package com.jxau.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

//@ConfigurationProperties(prefix = "jdbc")
@Data
public class JdbcProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private User user = new User();

    class User{
        private String name;
        private Integer age;
        private List languages;
    }



}
