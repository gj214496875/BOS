package com.itheima.web.action;

import com.itheima.bean.Noticebill;
import com.itheima.crm.service.Customer;
import com.itheima.crm.service.ICustomerService;
import com.itheima.service.INoticebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 业务受理单
 *
 * @author gj214
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
    @Autowired
    private INoticebillService noticebillService;
    @Autowired
    private ICustomerService customerService;

    /**
     * 添加业务受理单
     *
     * @return
     */
    public String add() {
        noticebillService.save(model);
        return LIST;
    }

    /**
     * 根据客户电话号码查询客户信息
     *
     * @return
     */
    public String findCustomerBytelephone() {
        Customer customer = customerService.findCustomerByTelephone(model.getTelephone());
        this.toJson(customer);
        return NONE;
    }
}
