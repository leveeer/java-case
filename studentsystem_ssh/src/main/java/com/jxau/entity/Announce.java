package com.jxau.entity;

import java.sql.Timestamp;

/**
 * ͨ��ʵ����
 * @author xie
 */
public class Announce {
    private int id;
    private String text;
    private String head;
    private Timestamp time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Announce{" + "id=" + id + ", text='" + text + '\'' + ", head='" + head + '\'' + ", time=" + time + '}';
    }
}
