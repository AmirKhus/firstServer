package com.example.demo.entity;

public class Buyer extends BaseEntity {
    private String first_name;
    private String surname;
    private String mail;
    private Integer region_id;

    public Buyer(Integer id, String first_name, String surname, String mail, Integer region_id) {
        super(id);
        this.first_name = first_name;
        this.surname = surname;
        this.mail = mail;
        this.region_id = region_id;
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
}
