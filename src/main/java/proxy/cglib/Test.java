package proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

public class Test {
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(Father.class);
		//		enhancer.setCallback(new MyMethodInterceptor());

		Father father = (Father)enhancer.create();
		System.out.println(father.say("I love you!"));
	}
}
