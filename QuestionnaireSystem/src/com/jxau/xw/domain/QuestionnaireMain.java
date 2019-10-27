package com.jxau.xw.domain;


import java.util.Date;

public class QuestionnaireMain {

    private int id;
    private String title;
    private Date createtime;
    private String isuse;
    private Date endtime;
    private String introduce;
    private String editor;

    public QuestionnaireMain(int id, String title, Date createtime, String isuse, Date endtime, String introduce, String editor) {
        this.id = id;
        this.title = title;
        this.createtime = createtime;
        this.isuse = isuse;
        this.endtime = endtime;
        this.introduce = introduce;
        this.editor = editor;
    }

    public QuestionnaireMain() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    @Override
    public String toString() {
        return "QuestionnaireMain{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createtime=" + createtime +
                ", isuse='" + isuse + '\'' +
                ", endtime=" + endtime +
                ", introduce='" + introduce + '\'' +
                ", editor='" + editor + '\'' +
                '}';
    }
}
