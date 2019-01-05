package lock;

public class Ticket extends Thread{

	private String name;

	//100张票
	private int count=100;

	public Ticket(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		new Ticket("A").start();
		new Ticket("B").start();
		new Ticket("C").start();
		new Ticket("D").start();
	}

	@Override public void run() {
		while (count>0) {
			System.out.println(name + "卖掉第 " + count-- + " 张票");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
