package com.itheima.dao;

import com.itheima.bean.Region;

import java.util.List;

public interface IRegionDao extends IBaseDao<Region> {
    List<Region> findByQ(String q);
}
