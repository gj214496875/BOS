package com.itheima.web.action;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itheima.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    protected static final String HOME = "home";
    protected static final String LIST = "list";
    protected PageBean pageBean = new PageBean();
    protected DetachedCriteria detachedCriteria =null;
    public void setPage(Integer page) {
        pageBean.setCurrentPage(page);
    }

    public void setRows(Integer rows) {
        pageBean.setPageSize(rows);
    }

    public void toJson(Object o){
       toJson(0,null);
    }

    public void toJson(Object o,String...strings){
        final List<String> list = Arrays.asList(strings);
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return list.contains(fieldAttributes.getName());
            }
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        }).create();
        String json = gson.toJson(o);
        try {
            ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
            ServletActionContext.getResponse().getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //模型对象
    protected T model;
    public T getModel() {
        return model;
    }

    //在构造方法中动态获取实体类型，通过反射创建model对象
    public BaseAction() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得BaseAction上声明的泛型数组
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        detachedCriteria = DetachedCriteria.forClass(entityClass);
        pageBean.setDetachedCriteria(detachedCriteria);
        //通过反射创建对象
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
