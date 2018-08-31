package com.itheima.service.impl;

import com.itheima.bean.Region;
import com.itheima.dao.IRegionDao;
import com.itheima.service.IRegionService;
import com.itheima.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private IRegionDao regionDao;

    public void saveBatch(List<Region> regions) {
        for (Region region : regions) {
            regionDao.saveOrUpdate(region);
        }
    }

    public void pageList(PageBean pageBean) {
        regionDao.pageList(pageBean);
    }

    public List<Region> findAll() {
        return regionDao.findAll();
    }

    public List<Region> findByQ(String q) {
        return regionDao.findByQ(q);
    }
}
