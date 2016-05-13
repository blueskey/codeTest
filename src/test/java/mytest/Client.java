package mytest;

import java.util.Random;

/**
 * Created by Administrator on 2016/2/16 0016.
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("常量会变哦：" + Const.RAND_CONST);
    }
}

interface Const {
    public static final int RAND_CONST = new Random().nextInt();
}
