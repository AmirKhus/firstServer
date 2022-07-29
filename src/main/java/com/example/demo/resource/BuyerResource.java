package com.example.demo.resource;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.Buyer;
import com.fasterxml.jackson.annotation.JsonInclude;

public class BuyerResource extends BaseResource {
    private Integer id;
    private String first_name;
    private String surname;
    private String mail;
    private Integer region_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RegionResource regionResource;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OrderResource[] orderResource;

    public BuyerResource() {
    }

    public BuyerResource(Buyer buyer) {
        this.id = buyer.getId();
        this.first_name = buyer.getFirst_name();
        this.surname = buyer.getSurname();
        this.mail = buyer.getMail();
        this.region_id = buyer.getRegion_id();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

    public RegionResource getRegionResource() {
        return regionResource;
    }

    public void setRegionResource(RegionResource regionResource) {
        this.regionResource = regionResource;
    }

    public OrderResource[] getOrderResource() {
        return orderResource;
    }

    public void setOrderResource(OrderResource[] orderResource) {
        this.orderResource = orderResource;
    }

    public Buyer toEntity() {
        return new Buyer(
        this.id,
        this.first_name,
        this.surname,
        this.mail,
        this.region_id
        );
    }
}
