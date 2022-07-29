package com.example.demo.entity;

public class Region extends BaseEntity {
    private String name;

    public Region(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
