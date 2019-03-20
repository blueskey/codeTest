package proxy.jdk;

import proxy.Child;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ChildProxy implements InvocationHandler {

	private Child child;

	public ChildProxy(Child child) {
		this.child = child;
	}

	@Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("我是child代理……。");

		return method.invoke(child, args);
	}
}
