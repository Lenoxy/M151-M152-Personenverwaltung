package ch.lu.bbzw.backendpersonenverwaltung;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TracingInterceptor implements MethodInterceptor{
    Object invoke(MethodInvocation i) throws Throwable{
        System.out.println("method " + i.getMethod() + " is called on " +
                i.getThis() + " with args " + i.getArguments());
        Object ret = i.proceed();
        System.out.println("method " + i.getMethod() + " returns " + ret);
        return ret;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable{
        return null;
    }
}
