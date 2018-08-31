package com.itheima.dao.impl;

import com.itheima.bean.Region;
import com.itheima.dao.IRegionDao;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {
    public List<Region> findByQ(String q) {
        String hql = "from Region where province like ? or city like ? or district like ? or " +
                "shortcode like ? or citycode like ?";
        return (List<Region>) this.getHibernateTemplate().find(hql,"%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
    }
}
