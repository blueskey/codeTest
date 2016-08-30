package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/8/30 0030.
 *
 * 达到线程死锁需要四个条件：
 *      互斥条件：一个资源每次只能被一个线程使用。
 *      资源独占条件：一个线程因请求资源而阻塞时，对已获得的资源保持不放。
 *      不剥夺条件：线程已获得的资源在未使用完之前，不能强行剥夺。
 *      循环等待条件：若干线程之间形成一种头尾相接的循环等待资源关系。
 *
 */
public class DeadLock implements Runnable {

    @Override
    public void run() {
        fun(10);
    }

    private synchronized void fun(int i) {
        if (--i > 0) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");

            }
            System.out.println(i);
            fun(i);
        }
    }

    public static void main(String[] args) {
        new Thread(new DeadLock()).start();//不会死锁

        //出现死锁
        final A a = new A();
        final B b = new B();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.a1(b);
            }
        }, "线程A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                b.b1(a);
            }
        }, "线程B").start();

    }

    static class A {
        public synchronized void a1(B b) {
            String name = Thread.currentThread().getName();
            System.out.println(name + "进入A.a1()");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
            System.out.println(name + "试图访问B.b2()");
            b.b2();
        }

        public synchronized void a2() {
            System.out.println("进入A.a2方法");
        }
    }

    static class B {

        private Lock lock = new ReentrantLock();
        public synchronized void b1(A a) {
        String name = Thread.currentThread().getName();
        System.out.println(name + "进入B.b1()");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        System.out.println(name + "试图访问A.a2()");
        a.a2();
    }

    public synchronized void b2() {
//        System.out.println("进入B.b2方法");

        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                System.out.println("进入B.b2方法");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    }
}




