package com.work1.model;

import javax.persistence.*;

@Entity
@Table(name = "Available")
public class Available {

    public Available() {
    }

    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;

    @ManyToOne
    private Storage storage;

    @ManyToOne
    private Product product;

    private int amount;

    public Available(Storage storage, Product product, int amount) {
        this.storage = storage;
        this.product = product;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public Storage getStorage() {
        return storage;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
