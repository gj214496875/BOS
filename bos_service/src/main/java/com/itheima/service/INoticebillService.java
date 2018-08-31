package com.itheima.service;

import com.itheima.bean.Noticebill;
import com.itheima.utils.PageBean;

/**
 * @author gj214
 */
public interface INoticebillService {
    /**
     * 添加业务受理单
     * @param model
     */
    void save(Noticebill model);


}
