package com.example.demo.resource;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.Region;
import com.fasterxml.jackson.annotation.JsonInclude;

public class RegionResource{
    private Integer id;
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BuyerResource[] buyerResource;

    public RegionResource() {}

    public RegionResource(Region region) {
        this.id = region.getId();
        this.name = region.getName();
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

    public BuyerResource[] getBuyerResource() {
        return buyerResource;
    }

    public void setBuyerResource(BuyerResource[] buyerResource) {
        this.buyerResource = buyerResource;
    }

    public Region toEntity() {
        return new Region(
                this.id,
                this.name
        );
    }

}
