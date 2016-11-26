package com.ray.lab6;

/**
 * Created by Ray on 2016/11/26.
 */
public class Person {
    private String name;
    private String primaryKey;
    private String type;

    public Person(String name, String primaryKey, String type) {
        this.name = name;
        this.primaryKey = primaryKey;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public String getType() {
        return type;
    }
}
