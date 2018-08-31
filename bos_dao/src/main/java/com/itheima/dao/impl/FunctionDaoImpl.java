package com.itheima.dao.impl;

import com.itheima.bean.Function;
import com.itheima.bean.Role;
import com.itheima.dao.IFunctionDao;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {
    @Override
    public List<Function> findAll() {
        String hql = "FROM Function f WHERE f.parentFunction IS NULL";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
        return list;
    }

    public List<Function> findByUserId(String id) {
        String hql = "SELECT DISTINCT f FROM Function f LEFT JOIN f.roles r  LEFT JOIN r.users u WHERE u.id = ?";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql,id);
        return list;
    }

    public List<Function> findAllMenu() {
        String hql = "FROM Function f WHERE f.generatemenu = '1' ORDER BY f.zindex DESC";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
        return list;
    }

    public List<Function> findMenuByUserId(String id) {
        String hql = "SELECT DISTINCT f FROM Function f LEFT JOIN f.roles r  LEFT JOIN r.users u WHERE u.id = ? AND f" +
                ".generatemenu = '1' ORDER BY f.zindex DESC";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql,id);
        return list;
    }
}
