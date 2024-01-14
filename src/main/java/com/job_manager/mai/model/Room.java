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

    @Column
    private String roomName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "member_rooms", joinColumns = {@JoinColumn(name = "room_id")}, inverseJoinColumns = {@JoinColumn(name = "member_id")})
    private Set<User> members;

    private int maxMemberCount;

    @OneToMany
    @JsonIgnore
    private Set<Message> messages = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "banned_user_rooms", joinColumns = {@JoinColumn(name = "room_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> bannedUser = new HashSet<>();

    @ManyToOne
    private User leader;

    @ManyToMany
    @JoinTable(name = "room_sub_leaders", joinColumns = {@JoinColumn(name = "room_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> subLeader = new HashSet<>();


    @OneToMany
    private Set<Media> media = new HashSet<>();

    public Room() {
        this.setId(UUID.randomUUID().toString());
    }

    public void addMember(User member) {
        this.members.add(member);
    }
}
