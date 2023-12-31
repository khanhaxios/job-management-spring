package com.job_manager.mai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    private long id;
    @Column(name = "role_name", nullable = false)
    private String roleName;

    @OneToMany
    @JsonIgnore
    private List<Account> accounts;
}
