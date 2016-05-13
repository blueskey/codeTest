package mytest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/15 0015.
 */
public class MemoryLeak {
    public static void main(String[] args) {
        Map<Key, String> map = new HashMap<Key, String>(10000);

        int counter = 0;
        while (true) {
            map.put(new Key("dummyKey"), "value");
            counter++;

            if (counter % 10000 == 0) {
                System.out.println("map size=" + map.size());
                System.out.println("free memonry after count " + counter + " is " + getFreeMemory() + " MB");

                sleep(1000);
            }
        }
    }

    static class Key {
        private String key;

        public Key(String key) {
            this.key = key;
        }

        /** 不加equals hashCode，则在用Map时很容易出现内存泄漏

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key1 = (Key) o;

            return !(key != null ? !key.equals(key1.key) : key1.key != null);

        }

        @Override
        public int hashCode() {
            return key != null ? key.hashCode() : 0;
        }
        **/
    }

    public static void sleep(long sleepFor) {
        try {
            Thread.sleep(sleepFor);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //get available memory in MB
    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (1024 * 1024);
    }

}

