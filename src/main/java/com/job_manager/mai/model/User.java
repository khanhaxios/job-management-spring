package com.job_manager.mai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "users")
@Entity
@Data
public class User {

    public User() {
        this.Id = UUID.randomUUID().toString();
    }

    @Id
    private String Id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String email;
    private String address;
    private Date birthday;

    private String department;

    @ManyToMany(mappedBy = "members")
    private Set<Room> rooms = new HashSet<>();
    @Column(unique = true)
    private String phone;

    private String avatar;

    public String getFullName() {
        return firstName + " " + lastName.trim();
    }

}
