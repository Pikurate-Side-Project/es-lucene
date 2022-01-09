package com.lucene.demo;

public class Person {

    private String id;
    private String name;
    private long age;

    public Person(String id, String name, long age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public long getAge() {
        return age;
    }
}
