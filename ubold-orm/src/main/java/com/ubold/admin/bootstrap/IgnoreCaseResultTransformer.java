package com.ubold.admin.bootstrap;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.hibernate.HibernateException;
import org.hibernate.transform.ResultTransformer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2018/6/5.
 */
public class IgnoreCaseResultTransformer implements ResultTransformer {

    private static final long serialVersionUID = -3779317531110592988L;

    private final Class<?> resultClass;
    private Field[] fields;
    private BeanUtilsBean beanUtilsBean;

    static {
        //注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空
        ConvertUtils.register(new SqlDateConverter(null), java.util.Date.class);
        ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
    }

    public IgnoreCaseResultTransformer(final Class<?> resultClass) {
        this.resultClass = resultClass;
        this.fields = this.getDeclaredFields(this.resultClass);
        beanUtilsBean=BeanUtilsBean.getInstance();
    }

    /**
     * aliases为每条记录的数据库字段名,ORACLE字段名默认为大写
     * tupe为与aliases对应的字段的值
     */
    public Object transformTuple(final Object[] tuple, final String[] aliases) {
        Object result;
        try {
            result = this.resultClass.newInstance();
            for (int i = 0; i < aliases.length; i++) {
                for (Field field : this.fields) {
                    String fieldName = field.getName();

                    //数据库字段带下划线的时候也能保证使用，如数据库字段为 USER_NAME，自定义pojo的属性名为username就可以使用
                    if (fieldName.equalsIgnoreCase(aliases[i].replaceAll("_", ""))) {
                        beanUtilsBean.setProperty(result, fieldName, tuple[i]);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new HibernateException("Could not instantiate resultclass: " + this.resultClass.getName(), e);
        }
        return result;
    }

    public List transformList(final List collection) {
        return collection;
    }

    private Field[] getDeclaredFields(Class<?> resultClass){
        List<Field> fieldList = new ArrayList<>() ;
        Class resultClass1 = resultClass;

        //当父类为null的时候说明到达了最上层的父类(Object类).
        while (resultClass1 != null) {
            fieldList.addAll(Arrays.asList(resultClass1.getDeclaredFields()));

            //得到父类,然后赋给自己
            resultClass1 = resultClass1.getSuperclass();
        }
        return fieldList.toArray(new Field[fieldList.size()]);
    }
}
