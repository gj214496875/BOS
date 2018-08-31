package com.itheima.service.impl;

import com.itheima.bean.Decidedzone;
import com.itheima.bean.Subarea;
import com.itheima.dao.IDecidedzoneDao;
import com.itheima.dao.ISubareaDao;
import com.itheima.service.IDecidedzoneService;
import com.itheima.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecidedzoneServiceImpl implements IDecidedzoneService {
    @Autowired
    private IDecidedzoneDao decidedzoneDao;
    @Autowired
    private ISubareaDao subareaDao;

    public void save(Decidedzone model, String[] subareaId) {
        for (String s : subareaId) {
            Subarea subareaById = subareaDao.findById(s);
            subareaById.setDecidedzone(model);
        }
        decidedzoneDao.save(model);
    }

    public void pageList(PageBean pageBean) {
        decidedzoneDao.pageList(pageBean);
    }
}
