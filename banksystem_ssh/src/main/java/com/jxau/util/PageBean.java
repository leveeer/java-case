package com.jxau.util;


import java.util.List;

/**
 * 分页工具类
 * @param <T>
 */
public class PageBean<T> {

    //当前页码
    private int currentPage;

    //总页数
    private Long totalPage;

    //每页显示的条数
    private int currentCount;

    //总条数
    private Long totalCount;

    //封装的数据
    private List<T> list;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
