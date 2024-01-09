package com.job_manager.mai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "members")
public class Member {
    @jakarta.persistence.Id
    private String Id;

    public Member() {
        this.Id = UUID.randomUUID().toString();
    }

    @OneToOne
    private User user;

    @JsonIgnore
    @ManyToMany(mappedBy = "members", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Room> chatRooms = new HashSet<>();
    private boolean isLeader;

    private boolean isOnline;
    private boolean isSubLeader;

    private boolean isBaned;
}
