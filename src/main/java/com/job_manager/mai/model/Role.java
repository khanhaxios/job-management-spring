package com.job_manager.mai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<Account> accounts;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Set<Permission> permissions = new HashSet<>();

    public void addPerm(Permission permission) {
        this.permissions.add(permission);
    }

    private boolean canAction;
}
