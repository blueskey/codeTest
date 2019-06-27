package lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SellTicketTest {



	public static void main(String[] args)  {
		int anInt=4;
		CyclicBarrier cyclicBarrier = new CyclicBarrier(anInt);
		for (int i = 0; i < anInt; i++) {

			new Thread(new Runnable() {
				@Override public void run() {
					try {
						cyclicBarrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					new SellTicket(Thread.currentThread().getName()).sell();

				}
			}).start();
		}
	}
}
