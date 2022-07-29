package com.example.demo.entity;

import java.sql.Timestamp;

public class Order extends BaseEntity {
    private  Integer buyer_id;
    private  Integer game_id;
    private  Timestamp date_order;

    public Order(Integer id, Integer buyer_id, Integer game_id, Timestamp date_order) {
        super(id);
        this.buyer_id = buyer_id;
        this.game_id = game_id;
        this.date_order = date_order;
    }

    public Integer getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(Integer buyer_id) {
        this.buyer_id = buyer_id;
    }

    public Integer getGame_id() {
        return game_id;
    }

    public void setGame_id(Integer game_id) {
        this.game_id = game_id;
    }

    public Timestamp getDate_order() {
        return date_order;
    }

    public void setDate_order(Timestamp date_order) {
        this.date_order = date_order;
    }
}
