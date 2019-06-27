package lock;

public class SellTicket {

	private static int num=100;

	private String name;

	public SellTicket(String name) {
		this.name = name;
	}

	public void sell() {
		while (true&num >0) {
			System.out.println(name+"卖掉第"+(num--)+"张票");
		}
	}
}
