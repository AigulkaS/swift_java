package com.work1.model;

import javax.persistence.*;

@Entity
@Table(name = "UsersOrder")
public class UsersOrder {

    public UsersOrder() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id_order")
    private long id;

    private String userName;

    @ManyToOne
    private Product product;

    private int amount;
    private boolean confirmed;
    private boolean purchase;

    public UsersOrder(String userName, Product product, int amount) {
        this.userName = userName;
        this.product = product;
        this.amount = amount;
        this.confirmed = false;
        this.purchase = false;
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

    public long getId() {
        return id;
    }

    public void setPurchase(boolean purchase) {
        this.purchase = purchase;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getUserName() {
        return userName;
    }
}
