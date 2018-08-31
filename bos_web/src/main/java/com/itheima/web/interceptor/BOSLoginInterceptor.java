package com.itheima.web.interceptor;

import com.itheima.bean.User;
import com.itheima.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * BOS系统登陆拦截器
 * @author 郭军
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User loginUser = BOSUtils.getLoginUser();
        if(loginUser==null){
            return "login";
        }
        return actionInvocation.invoke();
    }
}
