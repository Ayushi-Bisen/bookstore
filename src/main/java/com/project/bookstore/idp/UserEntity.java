package com.project.bookstore.idp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @Column(name="id")
    String userid;

    String username,  name,  password;

    @Column(name="ph_no")
    String phNo;

    UserEntity(String userid, String username, String name, String phNo, String password) {
        this.userid = userid;
        this.username = username;
        this.name = name;
        this.phNo = phNo;
        this.password = password;
    }

    UserEntity() {
    }
}
