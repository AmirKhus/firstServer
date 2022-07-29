package com.example.demo.resource;

import com.example.demo.entity.Company;
import com.fasterxml.jackson.annotation.JsonInclude;

public class CompanyResource extends BaseResource {
    private Integer id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private GameResource[] gameResource;

    public CompanyResource() {}

    public CompanyResource(Company company) {
        this.id = company.getId();
        this.name = company.getName();
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

    public Company toEntity() {
        return new Company(
                this.id,
                this.name
        );
    }
}
