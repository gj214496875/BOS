package com.itheima.service;

import com.itheima.utils.PageBean;

/**
 * @author gj214
 */
public interface IWorkbillService {
    /**
     * 分页查询
     * @param pageBean
     */
    void pageList(PageBean pageBean);
}
