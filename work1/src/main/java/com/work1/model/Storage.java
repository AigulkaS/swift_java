package com.work1.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Storage")
public class Storage {

    public Storage() {}

    @Id
    @GeneratedValue
    @Column(name = "id_storage")
    private long id;
    private String name;
    private String address;

    public Storage(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @JsonIgnore
    @OneToMany (fetch = FetchType.LAZY, mappedBy = "storage", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Available> availables = new ArrayList<>();

    public List<Available> getAvailables() {
        return availables;
    }

}
