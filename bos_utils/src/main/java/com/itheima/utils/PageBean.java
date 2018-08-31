package com.itheima.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * 分页通用类
 * @author gj214
 */
public class PageBean {

    /**
     * 当前页
     */
    private transient Integer currentPage;
    /**
     * 每页显示条数
     */
    private transient Integer pageSize;
    /**
     * 离线查询对象
     */
    private transient DetachedCriteria detachedCriteria;
    /**
     * 总数据数
     */
    private Integer total;
    /**
     * 要显示的分页数据
     */
    private List rows;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
