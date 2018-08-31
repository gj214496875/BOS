package com.itheima.service.impl;

import com.itheima.bean.Role;
import com.itheima.bean.User;
import com.itheima.dao.IUserDao;
import com.itheima.service.IUserService;
import com.itheima.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    public User login(User model) {
        User user = userDao.getUser(model.getUsername(), model.getPassword());
        return user;
    }

    public void editPassWord(String id, String password) {
        userDao.executeUpdate("user.editPassWord", password, id);
    }

    public void save(User model, String[] roleIds) {
        userDao.save(model);
        if (roleIds != null) {
            for (String roleId : roleIds) {
                Role role = new Role(roleId);
                model.getRoles().add(role);
            }
        }
    }

    public void pageList(PageBean pageBean) {
        userDao.pageList(pageBean);
    }
}
