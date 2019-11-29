package game;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class StartGame implements Runnable {

    private String player;

    private CyclicBarrier barrier;

    public StartGame(String player, CyclicBarrier barrier) {
        this.player = player;
        this.barrier = barrier;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println(this.getPlayer()+"开始匹配玩家．．");
        findOtherPlayers();
        try {
            barrier.await();

            System.out.println(this.getPlayer() + "开始选择角色");
            choiceRole();
            System.out.println(this.getPlayer() + "选择角色完毕");
            barrier.await();

            System.out.println(this.getPlayer() + "开始加载游戏");
            choiceRole();
            System.out.println(this.getPlayer() + "加载游戏完毕");
            barrier.await();

            start();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }

    private void start() {
        System.out.println("游戏开始．．．．");
    }

    private void choiceRole() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void findOtherPlayers() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {    try {
            System.out.println("阶段完成，等待2秒...");
            Thread.sleep(2000);
            System.out.println("进入下个阶段...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        });
        Thread player1 = new Thread(new StartGame("1",barrier));
        Thread player2 = new Thread(new StartGame("2",barrier));
        Thread player3 = new Thread(new StartGame("3",barrier));
        Thread player4 = new Thread(new StartGame("4",barrier));
        Thread player5 = new Thread(new StartGame("5",barrier));

        player1.start();
        player2.start();
        player3.start();
        player4.start();
        player5.start();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
