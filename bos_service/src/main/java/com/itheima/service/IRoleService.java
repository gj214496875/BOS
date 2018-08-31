package com.itheima.service;

import com.itheima.bean.Role;
import com.itheima.utils.PageBean;

import java.util.List;

public interface IRoleService {
    void save(Role model,String functionIds);

    void pageList(PageBean pageBean);

    List<Role> findAll();
}
