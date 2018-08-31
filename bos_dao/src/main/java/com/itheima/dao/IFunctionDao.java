package com.itheima.dao;

import com.itheima.bean.Function;

import java.util.List;

public interface IFunctionDao extends IBaseDao<Function> {
    List<Function> findByUserId(String id);

    List<Function> findAllMenu();

    List<Function> findMenuByUserId(String id);
}
