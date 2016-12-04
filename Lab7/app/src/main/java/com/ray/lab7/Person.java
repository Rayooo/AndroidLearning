package com.ray.lab7;

/**
 * Created by Ray on 2016/12/4.
 */
public class Person {
    private String name;
    private String mobile;
    private String id;

    public Person(String name, String mobile, String id) {
        this.name = name;
        this.mobile = mobile;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
