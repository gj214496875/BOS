package com.itheima.service;

import com.itheima.bean.Function;
import com.itheima.utils.PageBean;

import java.util.List;

public interface IFunctionService {
    List<Function> findAll();

    void save(Function model);

    void pageList(PageBean pageBean);

    List<Function> findMenu();
}
