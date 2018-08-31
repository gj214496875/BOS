package com.itheima.service.impl;

import com.itheima.bean.Function;
import com.itheima.bean.Role;
import com.itheima.dao.IRoleDao;
import com.itheima.service.IRoleService;
import com.itheima.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author gj214
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    public void save(Role model, String functionIds) {
        roleDao.save(model);
        //TODO 还未实现多表关联
        if (StringUtils.isNotBlank(functionIds)) {
            String[] ids = functionIds.split(",");
            for (String id : ids) {
                Function function = new Function();
                function.setId(id);
                model.getFunctions().add(function);
            }
        }
    }

    public void pageList(PageBean pageBean) {
        roleDao.pageList(pageBean);
    }

    public List<Role> findAll() {
        List<Role> roleList = roleDao.findAll();
        return roleList;
    }
}
