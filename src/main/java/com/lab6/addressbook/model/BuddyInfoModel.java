package com.lab6.addressbook.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table()
public class BuddyInfoModel implements Serializable {

    @Id()
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String number;

    public BuddyInfoModel(String name, String address, String number) {
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public BuddyInfoModel() {
        this.name = "";
        this.address = "";
        this.number = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        String str = "{name:" + name + ", address:" + address + ", number:" + number + "}";
        return str;
    }

    // Setting Name
    public void setName(String name) { this.name = name; }

    // Getting Name
    public String getName() {
        return name;
    }

    // Setting Address
    public void setAddress(String address) { this.address = address; }

    // Getting Address
    public String getAddress() {
        return address;
    }

    // Setting Number
    public void setNumber(String number) { this.number = number; }

    // Getting Number
    public String getNumber() {
        return number;
    }

}
