package com.itheima.service;

import com.itheima.bean.Staff;
import com.itheima.utils.PageBean;

import java.util.List;

public interface IStaffService {
    void addStaff(Staff model);

    void pageList(PageBean pageBean);

    void deleteStaff(String ids);

    void editStaff(Staff model);

    List<Staff> findByDeltag();
}
