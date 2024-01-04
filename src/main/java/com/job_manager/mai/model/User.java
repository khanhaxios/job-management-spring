package com.job_manager.mai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Table(name = "users")
@Entity
@Data
public class User {

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    @Id
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;
    private String address;
    private Date birthday;

    private String department;

    @Column(unique = true)
    private String phone;
    @OneToOne
    @JsonIgnore
    private Account account;

    public String getFullName() {
        return firstName + lastName;
    }

}
