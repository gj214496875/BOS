package com.itheima.service.impl;

import com.itheima.dao.IWorkbillDao;
import com.itheima.service.IWorkbillService;
import com.itheima.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gj214
 */
@Service
public class WorkbillServiceImpl implements IWorkbillService {
    @Autowired
    private IWorkbillDao workbillDao;
    /**
     * 分页查询
     *
     * @param pageBean
     */
    public void pageList(PageBean pageBean) {
        workbillDao.pageList(pageBean);
    }

}
