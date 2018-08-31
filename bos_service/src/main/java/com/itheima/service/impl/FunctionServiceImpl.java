package com.itheima.service.impl;

import com.itheima.bean.Function;
import com.itheima.bean.User;
import com.itheima.dao.IFunctionDao;
import com.itheima.service.IFunctionService;
import com.itheima.utils.BOSUtils;
import com.itheima.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FunctionServiceImpl implements IFunctionService {
    @Autowired
    private IFunctionDao functionDao;
    public List<Function> findAll() {
        List<Function> list = functionDao.findAll();
        return list;
    }

    public void save(Function model) {
        Function parentFunction = model.getParentFunction();
        if(parentFunction != null && parentFunction.getId().equals("")){
            model.setParentFunction(null);
        }
        functionDao.save(model);
    }

    public void pageList(PageBean pageBean) {
        functionDao.pageList(pageBean);
    }

    public List<Function> findMenu() {
        User user = BOSUtils.getLoginUser();
        List<Function> list;
        if(user.getUsername().equals("admin")){
           list = functionDao.findAllMenu();
        }else {
            list = functionDao.findMenuByUserId(user.getId());
        }
        return list;
    }
}
