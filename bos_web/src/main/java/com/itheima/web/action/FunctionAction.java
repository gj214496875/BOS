package com.itheima.web.action;

import com.itheima.bean.Function;
import com.itheima.service.IFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import java.util.List;

/**
 * @author gj214
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
    @Autowired
    private IFunctionService functionService;
    /**
     * 查询所有权限
     * @return
     */
    public String listAjax(){
       List<Function> list = functionService.findAll();
        for (Function function : list) {
            if(function.getParentFunction()!=null) {
                function.addPid();
            }
        }
       toJson(list,"zindex","roles","parentFunction");
        return NONE;
    }

    public String add(){
        functionService.save(model);
        return LIST;
    }

    public String pageList(){
        String page = model.getPage();
        pageBean.setCurrentPage(Integer.valueOf(page));
        functionService.pageList(pageBean);
        toJson(pageBean,"pid","roles","children");
        return NONE;
    }

    public String findMenu(){
        List<Function> list = functionService.findMenu();
        for (Function function : list) {
            if(function.getParentFunction()!=null) {
                function.addPid();
            }
        }
        toJson(list,"children","roles","parentFunction");
        return NONE;
    }
}
