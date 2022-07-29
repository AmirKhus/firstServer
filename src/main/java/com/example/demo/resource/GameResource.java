package com.example.demo.resource;

import com.example.demo.entity.Game;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Timestamp;

public class GameResource extends BaseResource {
    private Integer id;
    private String name;
    private Integer developer_id;
    private Integer publisher_id;
    private Integer genre_id;
    private Float price;
    private Timestamp date_of_publication;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CompanyResource publisher, developer;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GenreResource genreResource;

//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private GameResource gameResource;
//
//    public GameResource getGameInfomation() {
//        return gameResource;
//    }
//
//    public void setGameInfomation(GameResource gameResource) {
//        this.gameResource = gameResource;
//    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderResource[] orderResource;

    public GameResource() {}

    public GameResource(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.developer_id = game.getDeveloper_id();
        this.publisher_id = game.getPublisher_id();
        this.genre_id = game.getGenre_id();
        this.price = game.getPrice();
        this.date_of_publication = game.getDate_of_publication();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public CompanyResource getPublisher() {
        return publisher;
    }

    public void setPublisher(CompanyResource publisher) {
        this.publisher = publisher;
    }

    public CompanyResource getDeveloper() {
        return developer;
    }

    public void setDeveloper(CompanyResource developer) {
        this.developer = developer;
    }

    public GenreResource getGenreResource() {
        return genreResource;
    }

    public void setGenreResource(GenreResource genreResource) {
        this.genreResource = genreResource;
    }

    public OrderResource[] getOrderResource() {
        return orderResource;
    }

    public void setOrderResource(OrderResource[] orderResource) {
        this.orderResource = orderResource;
    }

    public Game toEntity() {
        return new Game(
        this.id,
        this.name,
        this.developer_id,
        this.publisher_id,
        this.genre_id,
        this.price,
        this.date_of_publication
        );
    }
}

