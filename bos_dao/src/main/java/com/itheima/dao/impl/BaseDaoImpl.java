package com.itheima.dao.impl;

import com.itheima.dao.IBaseDao;
import com.itheima.utils.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
    private Class<T> tClass;

    public BaseDaoImpl() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types = superclass.getActualTypeArguments();
        tClass = (Class<T>) types[0];
    }
    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    public void save(T t) {
        this.getHibernateTemplate().save(t);
    }

    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    public void delete(T t) {
        this.getHibernateTemplate().delete(t);
    }

    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(tClass, id);
    }

    public List<T> findAll() {
        String hql = "from " + tClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    public void executeUpdate(String queryName, Object... objects) {
        Session currentSession = getSessionFactory().getCurrentSession();
        Query query = currentSession.getNamedQuery(queryName);
        int i = 0;
        for (Object o : objects) {
            query.setParameter(i++, o);
        }
        query.executeUpdate();
    }

    /**
     * 通用分页查询
     *
     * @param pageBean
     */
    public void pageList(PageBean pageBean) {
        Integer currentPage = pageBean.getCurrentPage();
        Integer pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> totals = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long aLong = totals.get(0);
        pageBean.setTotal(aLong.intValue());
        detachedCriteria.setProjection(null);
        //指定hibernate封装对象的方式
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        List list = getHibernateTemplate().findByCriteria(detachedCriteria, (currentPage - 1) * pageSize, pageSize);
        pageBean.setRows(list);
    }

    public void saveOrUpdate(T t) {
        this.getHibernateTemplate().saveOrUpdate(t);
    }

    public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
        return (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
