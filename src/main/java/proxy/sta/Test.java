package proxy.sta;

import proxy.Person;

public class Test {
	public static void main(String[] args) {
		Person person = new ChildProxy();
		person.speek("我饿了……");
	}
}
