package com.itheima.service.impl;

import com.itheima.Enum.OrderType;
import com.itheima.Enum.PickstateType;
import com.itheima.Enum.WorkType;
import com.itheima.bean.*;
import com.itheima.crm.service.ICustomerService;
import com.itheima.dao.IDecidedzoneDao;
import com.itheima.dao.INoticebillDao;
import com.itheima.dao.IWorkbillDao;
import com.itheima.service.INoticebillService;
import com.itheima.utils.BOSUtils;
import com.itheima.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @author gj214
 */
@Service
public class NoticebillServiceImpl implements INoticebillService {
    @Autowired
    private INoticebillDao noticebillDao;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IDecidedzoneDao decidedzoneDao;
    @Autowired
    private IWorkbillDao workbillDao;

    /**
     * 添加业务受理单
     *
     * @param model
     */
    public void save(Noticebill model) {
        //获得受理人信息
        User loginUser = BOSUtils.getLoginUser();
        model.setUser(loginUser);
        //保存业务受理单
        noticebillDao.save(model);
        //根据地址查询定区id
        String decidedzoneId = customerService.findDecidedzoneIdByAddress(model.getPickaddress());
        if(decidedzoneId!=null){
            //根据地址查询到了相应定区，将完成自动分单
            model.setOrdertype(OrderType.ORDERTYPE_AUTO.toString());
            //根据查询到的定区ID查询对应的取派员
            Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
            //分配取派员
            Staff staff = decidedzone.getStaff();
            model.setStaff(staff);
            //为取派员创建一个工单
            Workbill workbill = new Workbill();
            //关联业务受理单
            workbill.setNoticebill(model);
            //追单次数为0次
            workbill.setAttachbilltimes(0);
            //创建时间为当前系统时间
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            //关联取派员
            workbill.setStaff(staff);
            //取件状态
            workbill.setPickstate(PickstateType.PICKSTATE_NO.toString());
            //工单状态
            workbill.setType(WorkType.NEW_ORDER.toString());
            //保存工单
            workbillDao.save(workbill);
        }else {
            //根据地址没查询到相应定区，不能完成自动分单
            model.setOrdertype(OrderType.ORDERTYPE_MAN.toString());
        }
    }
}
