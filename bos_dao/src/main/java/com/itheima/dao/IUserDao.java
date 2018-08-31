package com.itheima.dao;

import com.itheima.bean.User;

public interface IUserDao extends IBaseDao<User> {
    User getUser(String username, String password);
    User getUser(String username);
}
