package com.itheima.service;

import com.itheima.bean.Decidedzone;
import com.itheima.utils.PageBean;

public interface IDecidedzoneService {
    void save(Decidedzone model, String[] subareaId);

    void pageList(PageBean pageBean);
}
