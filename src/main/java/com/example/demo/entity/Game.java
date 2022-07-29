package com.example.demo.entity;

import java.sql.Timestamp;

public class Game extends BaseEntity {
    private String name;
    private Integer developer_id;
    private Integer publisher_id;
    private Integer genre_id;
    private Float price;
    private Timestamp date_of_publication;

    public Game(Integer id, String name, Integer developer_id, Integer publisher_id,
                Integer genre_id, Float price, Timestamp date_of_publication) {
        super(id);
        this.name = name;
        this.developer_id = developer_id;
        this.publisher_id = publisher_id;
        this.genre_id = genre_id;
        this.price = price;
        this.date_of_publication = date_of_publication;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(Integer developer_id) {
        this.developer_id = developer_id;
    }

    public Integer getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(Integer publisher_id) {
        this.publisher_id = publisher_id;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Timestamp getDate_of_publication() {
        return date_of_publication;
    }

    public void setDate_of_publication(Timestamp date_of_publication) {
        this.date_of_publication = date_of_publication;
    }
}

