package com.job_manager.mai.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class RoomTag {
    @jakarta.persistence.Id
    private long Id;
    private String name;

    @ManyToMany
    @JoinTable(name = "rooms_tags",joinColumns = @JoinColumn(name = "tag_id"),inverseJoinColumns = @JoinColumn(name = "room_id"))
    private Set<Room> rooms = new HashSet<>();
}
