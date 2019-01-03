package proxy;

import annotation.Delete;
import annotation.Insert;
import annotation.Select;
import annotation.Update;
import config.MysqlConfig;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @Author Martin Ma
 * @Date 2018/12/29
 **/
public class DynamicProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String sql = null;
        if (method.isAnnotationPresent(Select.class)) {
            Class returnTypeClass = null;
            sql = method.getAnnotation(Select.class).value();
            returnTypeClass = method.getReturnType();
            if (List.class.isAssignableFrom(returnTypeClass)) {
                returnTypeClass = (Class<?>) ((ParameterizedType)method.getGenericReturnType())
                        .getActualTypeArguments()[0];
                return goQuery(sql, args, returnTypeClass);
            }
        } else if (method.isAnnotationPresent(Insert.class)) {
            sql = method.getAnnotation(Insert.class).value();
        } else if (method.isAnnotationPresent(Update.class)) {
            sql = method.getAnnotation(Update.class).value();
        } else {
            sql = method.getAnnotation(Delete.class).value();
        }
        sqlExecute(sql, args);

        return null;
    }

    private void sqlExecute(String sql, Object[] args) {

    }

    private Object goQuery(String sql, Object[] args, Class returnTypeClass) {

        return null;
    }

}
