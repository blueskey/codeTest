package proxy.sta;

import proxy.Child;
import proxy.Person;

public class ChildProxy implements Person {

	private Child child=new Child();

	@Override public String speek(String s) {
		System.out.println("ChildProxy代表小娃娃说话……");
		return child.speek(s);
	}

	@Override public void chiFan() {
		System.out.println("ChildProxy代表小娃娃吃饭……");
	}
}
