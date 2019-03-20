package proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Father implements MethodInterceptor {
	@Override public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
			throws Throwable {
		return methodProxy.invokeSuper(o, objects);
	}

	public String  say(String s) {
		return "爸爸说……" + s;
	}
}
