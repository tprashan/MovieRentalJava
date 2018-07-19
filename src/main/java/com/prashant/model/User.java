package com.prashant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue()
    private String id;
    @Column(name = "uname", unique = true)
    private String username;
    @Column(name = "email", unique = true)
    private String emailId;
    @Column(name = "password")
    private String password;

    public User(String uname, String email, String password){
        this.username = uname;
        this.emailId = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }
}
