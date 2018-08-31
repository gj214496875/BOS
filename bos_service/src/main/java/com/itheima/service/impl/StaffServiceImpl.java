package com.itheima.service.impl;

import com.itheima.bean.Staff;
import com.itheima.dao.IStaffDao;
import com.itheima.service.IStaffService;
import com.itheima.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 取派员service
 *
 * @author 郭军
 */
@Service
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private IStaffDao staffDao;

    public void addStaff(Staff model) {
        staffDao.save(model);
    }

    public void pageList(PageBean pageBean) {
        staffDao.pageList(pageBean);
    }

    public void deleteStaff(String ids) {
        String[] split = ids.split(",");
        for (String s:split){
            staffDao.executeUpdate("staff.delete",s);
        }
    }

    public void editStaff(Staff model) {
        Staff byId = staffDao.findById(model.getId());
        byId.setName(model.getName());
        byId.setHaspda(model.getHaspda());
        byId.setStandard(model.getStandard());
        byId.setStation(model.getStation());
        byId.setTelephone(model.getTelephone());
        staffDao.update(byId);
    }

    public List<Staff> findByDeltag() {
        DetachedCriteria dc = DetachedCriteria.forClass(Staff.class);
        dc.add(Restrictions.eq("deltag","0"));
        return staffDao.findByCriteria(dc);
    }
}
