package com.springapp.mvc.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2015/9/29 0029.
 */
public class UserDo implements Serializable {
    private String name;

    private int age;

    private Date birthday;

    public UserDo() {

    }

    public UserDo(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
