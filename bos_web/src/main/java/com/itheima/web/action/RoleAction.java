package com.itheima.web.action;

import com.itheima.bean.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author gj214
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
    @Autowired
    private IRoleService roleService;
    private String functionIds;

    public String add() {
        roleService.save(model, functionIds);
        return LIST;
    }
    public String pageList(){
        roleService.pageList(pageBean);
        toJson(pageBean,"functions","users");
        return NONE;
    }
    public String listAjax(){
        List<Role> list = roleService.findAll();
        toJson(list,"functions","users");
        return NONE;
    }
    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }
}
