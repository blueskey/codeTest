package game;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.List;

public class User {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static void main(String[] args) {
        List<User> us = Lists.newArrayList();
        User user = new User();
        System.out.println(user);
        user.setAge(11);
        user.setName("hh");
        us.add(user);

        User user2 = new User();
        System.out.println(JSONObject.toJSONString(us));
        for (User u : us) {
            System.out.println(u);
//            u = null;
            u = user2;
            System.out.println(u);
        }

        System.out.println(JSONObject.toJSONString(us));
    }
}
