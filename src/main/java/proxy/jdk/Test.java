package proxy.jdk;

import proxy.Child;
import proxy.Person;

import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		Person child = (Person) Proxy.newProxyInstance(ChildProxy.class.getClassLoader(), // 1. 类加载器
				new Class<?>[] {Person.class}, // 2. 代理需要实现的接口，可以有多个
				new ChildProxy(new Child()));// 3. 方法调用的实际处理者
		System.out.println(child.speek("I love you!"));
	}
}
