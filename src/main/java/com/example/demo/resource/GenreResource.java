package com.example.demo.resource;

import com.example.demo.entity.Genre;
import com.fasterxml.jackson.annotation.JsonInclude;

public class  GenreResource extends BaseResource {
    private Integer id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GameResource[] gameResource;

    public GenreResource() {}

    public GenreResource(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
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

    public GameResource[] getGameResource() {
        return gameResource;
    }

    public void setGameResource(GameResource[] gameResource) {
        this.gameResource = gameResource;
    }

    public Genre toEntity() {
        return new Genre(
                this.id,
                this.name
        );
    }
}
