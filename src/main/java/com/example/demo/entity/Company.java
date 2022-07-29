package com.example.demo.entity;

public class Company extends BaseEntity {
    private String name;

    public Company(Integer id, String sourceId) {
        super(id);
        this.name = sourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
