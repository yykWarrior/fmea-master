package com.rb.fmea.page;

/**
 * @version v1.0
 * @ClassName: PageParameter
 * @Description: TODO 分页参数封装实体
 * @Author: yyk
 * @Date: 2020/3/2 16:25
 */
public class PageParameter {
    private int page;
    private int limit;


    public PageParameter() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public PageParameter(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }
}
