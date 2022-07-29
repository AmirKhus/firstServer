package com.example.demo.resource;

import com.example.demo.entity.Order;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

public class OrderResource extends BaseResource {
    private Integer id;
    private  Integer buyer_id;
    private  Integer game_id;
    private  Timestamp date_order;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BuyerResource buyerInfomation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GameResource gameInfomation;

    public OrderResource() {}

    public OrderResource(Order order) {
        this.id = order.getId();
        this.buyer_id = order.getBuyer_id();
        this.game_id = order.getGame_id();
        this.date_order = order.getDate_order();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BuyerResource getBuyerInfomation() {
        return buyerInfomation;
    }

    public void setBuyerInfomation(BuyerResource buyerInfomation) {
        this.buyerInfomation = buyerInfomation;
    }

    public GameResource getGameInfomation() {
        return gameInfomation;
    }

    public void setGameInfomation(GameResource gameInfomation) {
        this.gameInfomation = gameInfomation;
    }

    public Order toEntity() {
        return new Order(
                this.id,
                this.buyer_id,
                this.game_id,
                this.date_order
        );
    }
}
