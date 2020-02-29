package xiaohui;


import com.google.common.collect.Sets;

import java.util.Set;

public class Coll {

    public static void main(String[] args) {
        Set<Integer> a = Sets.newHashSet(2, 3, 5, 6, 7, 4, 99);
        Set<Integer> b = Sets.newHashSet(2, 13, 5, 16, 27, 4,43);

        System.out.println(Sets.difference(a,b));
        System.out.println(Sets.difference(b,a));

    }
}
