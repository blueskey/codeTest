package lock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class TicketTest {

	private static   RedissonClient redissonClient;

	//100张票
	private static int  count=1000;

	static class SellTicket extends Thread{
		private String name;

		public SellTicket(String name) {
			this.name = name;
		}

		@Override public void run() {
//			RLock lock = redissonClient.getLock("lock");
//			try {
//
//				while (count>0) {
//					lock.lock();
//					System.out.println(name + "卖掉第 " + count-- + " 张票");
//					Thread.sleep(5000);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}finally {
//				lock.unlock();
//			}
//			RLock lock = redissonClient.getLock("lock");
//			lock.lock();
//			try {
//				while (count >0) {
//					System.out.println(name + "卖掉第 " + count-- + " 张票");
//				}
//			}finally {
//				lock.unlock();
//			}
//		}

			synchronized (this) {
				while (true&count >0) {
					System.out.println(name + "卖掉第 " + count-- + " 张票");
				}
			}
		}
	}

	private static org.redisson.api.RedissonClient init() {
		Config config = new Config();
		config.useSentinelServers() .setMasterName("mymaster").addSentinelAddress("192.168.40.128:26001","192.168.40.128:26002");
		return Redisson.create(config);
	}

	public static void main(String[] args) {
		redissonClient = init();
		new SellTicket("A").start();
		new SellTicket("B").start();
		new SellTicket("C").start();
		new SellTicket("D").start();

	}


}




