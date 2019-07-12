package com.work1.model;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class Users {

    public Users() {}

    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private long id;
    private String login;
    private String password;

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
