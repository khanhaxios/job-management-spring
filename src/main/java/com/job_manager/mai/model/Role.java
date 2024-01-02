package com.job_manager.mai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "role_name", nullable = false)
    private String roleName;

    @OneToMany
    @JsonIgnore
    private List<Account> accounts;

    @ManyToMany
    private List<Permission> permissions;
}
