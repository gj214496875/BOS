package com.itheima.web.action;

import com.itheima.bean.User;
import com.itheima.service.IUserService;
import com.itheima.utils.BOSUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @author 郭军
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>  {
    @Autowired
    private IUserService userService;
    /**
     *获得页面上的验证码
     */
    private String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    /**
    * 用户登陆方法
    */
    public String login() {
        String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        if (StringUtils.isNotBlank(checkcode) && key.equals(checkcode)) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());
            try {
                subject.login(token);
                User user = (User) subject.getPrincipal();
                ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return LOGIN;
            }
            return HOME;
        } else {
            this.addActionError("验证码错误！");
            return LOGIN;
        }
    }
    /**
    * 用户登陆方法
    */
    public String login_back() {
        String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        if (StringUtils.isNotBlank(checkcode) && key.equals(checkcode)) {
            User user = userService.login(model);
            if (user != null) {
                //用户登陆成功
                ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
                return HOME;
            } else {
                //用户登陆失败
                this.addActionError("用户名或密码错误！");
                return LOGIN;
            }
        } else {
            this.addActionError("验证码错误！");
            return LOGIN;
        }
    }

    /**
     * 用户注销方法
     */
    public String exit() {
        ServletActionContext.getRequest().getSession().removeAttribute("loginUser");
        return LOGIN;
    }
    /**
     * 修改用户密码
    */
    public void editPassWord() throws IOException {
        String password = model.getPassword();
        String id = BOSUtils.getLoginUser().getId();
        String f = "1";
        try {
            userService.editPassWord(id,password);
        } catch (Exception e) {
            f = "0";
            e.printStackTrace();
        }
        ServletActionContext.getResponse().getWriter().print(f);
    }

    private String[] roleIds;

    public String add(){
        userService.save(model,roleIds);
        return LIST;
    }
    public String pageList(){
        userService.pageList(pageBean);
        toJson(pageBean,"noticebills","roles");
        return NONE;
    }
    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }
}
