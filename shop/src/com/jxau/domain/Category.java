package com.jxau.domain;

import java.io.Serializable;

/**
 * @ClassName Category
 * @Description TODO
 * @Author xie
 * @Date 2019/8/22 20:11
 * @Version 1.0
 */
public class Category implements Serializable {
    private String cid;
    private String cname;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" + "cid='" + cid + '\'' + ", cname='" + cname + '\'' + '}';
    }
}
