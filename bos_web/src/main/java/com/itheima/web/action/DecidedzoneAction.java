package com.itheima.web.action;

import com.itheima.bean.Decidedzone;
import com.itheima.crm.service.Customer;
import com.itheima.crm.service.ICustomerService;
import com.itheima.service.IDecidedzoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class DecidedzoneAction extends BaseAction<Decidedzone> {
    @Autowired
    private IDecidedzoneService decidedzoneService;
    @Autowired
    private ICustomerService customerService;
    private String[] subareaId;
    private List<Integer> customerIds;

    /**
     * 添加定区
     *
     * @return
     */
    public String add() {
        decidedzoneService.save(model, subareaId);
        return LIST;
    }

    /**
     * 定区分页查询
     *
     * @return
     */
    public String pageList() {
        decidedzoneService.pageList(pageBean);
        toJson(pageBean, "subareas", "decidedzones");
        return NONE;
    }

    /**
     * 查询没有关联定区的客户
     *
     * @return
     */
    public String findNotDecidedzoneId() {
        List<Customer> notDecidedzoneId = customerService.findNotDecidedzoneId();
        toJson(notDecidedzoneId);
        return NONE;
    }

    /**
     * 查询已经关联选定定区的客户
     *
     * @return
     */
    public String findHasDecidedzoneId() {
        List<Customer> hastDecidedzoneId = customerService.findHasDecidedzoneId(model.getId());
        toJson(hastDecidedzoneId);
        return NONE;
    }

    /**
     * 定区关联客户
     *
     * @return
     */
    public String assignCustomerstoDecidedzone() {
        customerService.saveOrUpdate(model.getId(), customerIds);
        return LIST;
    }

    public void setSubareaId(String[] subareaId) {
        this.subareaId = subareaId;
    }


    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }
}
