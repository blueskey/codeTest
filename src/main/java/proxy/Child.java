package proxy;

public class Child implements Person {
	@Override public String speek(String s) {
		System.out.println("小娃娃说话，说不清楚……" + s);
		return s;
	}

	@Override public void chiFan() {
		System.out.println("小娃娃吃饭，吃得满嘴……");
	}
}
