package com.itheima.dao.impl;

import com.itheima.bean.User;
import com.itheima.dao.IUserDao;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
    public User getUser(String username, String password) {
        String hql = "from User where username = ? and password = ?";
        List<User> list = (List<User>) getHibernateTemplate().find(hql, username, password);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public User getUser(String username) {
        String hql = "from User where username = ?";
        List<User> list = (List<User>) getHibernateTemplate().find(hql, username);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
