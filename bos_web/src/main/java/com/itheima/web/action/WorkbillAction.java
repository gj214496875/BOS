package com.itheima.web.action;

import com.itheima.bean.Workbill;
import com.itheima.service.IWorkbillService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author gj214
 */
@Controller
@Scope("prototype")
public class WorkbillAction extends BaseAction<Workbill>{
    private IWorkbillService workbillService;
    public String pageList(){
        workbillService.pageList(pageBean);
        toJson(pageBean,"noticebill","decidedzones");
        return NONE;
    }
}
