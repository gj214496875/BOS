package com.itheima.realm;

import com.itheima.bean.Function;
import com.itheima.bean.User;
import com.itheima.dao.IFunctionDao;
import com.itheima.dao.IUserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author gj214
 */
public class BOSRealm extends AuthorizingRealm {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IFunctionDao functionDao;
    /**
     * 授权方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得登陆的用户对象
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String root = "admin";
        List<Function> list;
        if (root.equals(user.getUsername())) {
            System.out.println("管理员权限");
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Function.class);
            list = functionDao.findByCriteria(detachedCriteria);
        }else {
            System.out.println("普通权限");
            list = functionDao.findByUserId(user.getId());
        }
        for (Function function : list) {
            info.addStringPermission(function.getCode());
        }
        return info;
    }

    /**
     * 认证方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) authenticationToken;
        User user = userDao.getUser(passwordToken.getUsername());
        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return info;
    }
}
