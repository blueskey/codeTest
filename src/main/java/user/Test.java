package user;

import com.google.common.collect.Sets;

import java.util.Set;

public class Test {
    public static void main(String[] args) {

        Level1 level1 = new Level1();
        level1.upgrade();

        Set<Long> userIds =Sets.newHashSet(1804528L, 1804518L, 1804516L, 1804510L, 1804509L, 826091L, 752239L, 311438L, 186839L);
        Set<Long> existRecordUserIds = Sets.newHashSet(1804528L, 1804518L, 1804516L, 1804510L, 1804509L, 826091L, 752239L, 311438L, 186839L);
        Set<Long> needAdd = Sets.difference(userIds, existRecordUserIds);
        System.out.println(needAdd);
        Set<Long> needUpdate = Sets.difference(userIds, needAdd);
        System.out.println(needUpdate);

        Upgrade user1 = new Level1();
        Upgrade user2 = new Level1();
        user1.upgrade();
        user2.upgrade();

    }
}
