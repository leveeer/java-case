package com.jxau.service.pojo;

import lombok.Data;
import java.io.Serializable;

@Data
public class Student implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private String sex;
    private String email;

}
