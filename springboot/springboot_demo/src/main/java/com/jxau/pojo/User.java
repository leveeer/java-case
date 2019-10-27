package com.jxau.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "t_user")

public class User {

    @Id         //主键
    @KeySql(useGeneratedKeys = true)        //主键自增
    private Long id;
    private String username;
    private String password;
    private Integer age;
    private Date birthday;

    @Transient          //不用持久化到数据库
    private String note;

}
