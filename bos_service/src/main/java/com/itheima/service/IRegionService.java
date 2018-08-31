package com.itheima.service;

import com.itheima.bean.Region;
import com.itheima.utils.PageBean;

import java.util.List;

public interface IRegionService {
    void saveBatch(List<Region> regions);

    void pageList(PageBean pageBean);

    List<Region> findAll();

    List<Region> findByQ(String q);
}
