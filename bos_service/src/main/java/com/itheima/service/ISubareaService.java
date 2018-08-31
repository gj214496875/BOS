package com.itheima.service;

import com.itheima.bean.Subarea;
import com.itheima.utils.PageBean;

import java.util.List;

public interface ISubareaService {
    void save(Subarea model);

    void pageList(PageBean pageBean);

    List<Subarea> findAll();

    List<Subarea> findByDecidedzone();
    List<Subarea> findByDecidedzone(String decidedzoneId);
}
