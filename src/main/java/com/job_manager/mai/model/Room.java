package com.job_manager.mai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "rooms")
public class Room {
    @jakarta.persistence.Id
    private String Id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "member_rooms", joinColumns = {@JoinColumn(name = "room_id")}, inverseJoinColumns = {@JoinColumn(name = "member_id")})
    private Set<Member> members;

    private int maxMemberCount;

    @OneToMany
    private Set<Message> messages = new HashSet<>();

    public Room() {
        this.setId(UUID.randomUUID().toString());
    }
}
