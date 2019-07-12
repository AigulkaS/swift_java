package com.work1.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Product")
public class Product {

    public Product() {}

    @Id
    @GeneratedValue
    @Column(name = "id_product")
    private long id;
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return super.toString() + "{ id = " + id + " name = " + name  + " price = " + price + " }";
    }

    @JsonIgnore
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Available> availables = new ArrayList<>();

    public List<Available> getAvailables() {
        return availables;
    }
}
