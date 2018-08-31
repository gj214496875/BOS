package com.itheima.service.impl;

import com.itheima.bean.Subarea;
import com.itheima.dao.ISubareaDao;
import com.itheima.service.ISubareaService;
import com.itheima.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubareaServiceImpl implements ISubareaService {
    @Autowired
    private ISubareaDao subareaDao;

    public void save(Subarea model) {
        subareaDao.save(model);
    }

    public void pageList(PageBean pageBean) {
        subareaDao.pageList(pageBean);
    }

    public List<Subarea> findAll() {
        List<Subarea> list = subareaDao.findAll();
        return list;
    }

    public List<Subarea> findByDecidedzone() {
        DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
        dc.add(Restrictions.isNull("decidedzone"));
        return subareaDao.findByCriteria(dc);
    }
    public List<Subarea> findByDecidedzone(String decidedzoneId) {
        DetachedCriteria dc = DetachedCriteria.forClass(Subarea.class);
        dc.add(Restrictions.eq("decidedzone.id",decidedzoneId));
        return subareaDao.findByCriteria(dc);
    }

}
