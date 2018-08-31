package com.itheima.dao;

import com.itheima.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    void save(T t);
    void update(T t);
    void delete(T t);
    T findById(Serializable id);
    List<T> findAll();
    void executeUpdate(String queryName,Object...objects);
    void pageList(PageBean pageBean);
    void saveOrUpdate(T t);
    List<T> findByCriteria(DetachedCriteria detachedCriteria);
}
