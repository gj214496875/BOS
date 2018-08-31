package com.itheima.web.action;

import com.itheima.bean.Staff;
import com.itheima.service.IStaffService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * 取派员action
 *
 * @author 郭军
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    @Autowired
    private IStaffService staffService;

    /**
     * 添加取派员
     */
    public String add() {
        staffService.addStaff(model);
        return LIST;
    }



    public String pageList() throws IOException {
        staffService.pageList(pageBean);
        this.toJson(pageBean,"decidedzones");
        return NONE;
    }
    private String ids;

    @RequiresPermissions("staff-delete")
    public String delete(){
        staffService.deleteStaff(ids);
        return LIST;
    }

    public String edit(){
        staffService.editStaff(model);
        return LIST;
    }

    public String listAjax(){
        List<Staff> list = staffService.findByDeltag();
        toJson(list,"decidedzones");
        return NONE;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
