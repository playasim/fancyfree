package proxy;

import java.lang.reflect.Proxy;

/**
 * @Author Martin Ma
 * @Date 2018/12/29
 **/
public class DyanamicProxyFactory {

    public static <T> T getBean(Class<T> clazz) {
        DynamicProxy dynamicProxy = new DynamicProxy();
        return (T) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{clazz},
                dynamicProxy
                );
    }

}
