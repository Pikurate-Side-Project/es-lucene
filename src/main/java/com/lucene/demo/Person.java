package com.lucene.demo;

public class Person {

    private String id;
    private String name;
    private String description;
    private long age;

    public Person(String id, String name, String description, long age) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.age = age;
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getAge() {
        return age;
    }
}
