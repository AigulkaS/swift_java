package com.work1.model;


import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Delivery")
public class Delivery {

    public Delivery() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id_invoice")
    private long id;

    private String date;

    @ManyToOne
    private Storage storage;

    @ManyToOne
    private Product product;

    private int amount;

    public Delivery(Product product, Storage storage, int amount) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        date = dateFormat.format(new Date());
        this.storage = storage;
        this.product = product;
        this.amount = amount;
    }

    public String getDate() {
        return date;
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
}
