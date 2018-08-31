package com.itheima.service;

import com.itheima.bean.User;
import com.itheima.utils.PageBean;

public interface IUserService {
    User login(User user);

    void editPassWord(String id, String password);

    void save(User model,String[] roleIds);

    void pageList(PageBean pageBean);
}
