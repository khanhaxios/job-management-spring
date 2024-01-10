package com.job_manager.mai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "messages")
public class Message {

    @Id
    private String id;

    @Column(nullable = false)
    private String content;

    @Nullable
    @OneToMany
    private Set<Media> media = new HashSet<>();

    @ManyToOne
    private Member sender;

    private LocalDateTime sentAt;

    public Message() {
        this.id = UUID.randomUUID().toString();
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Room room;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "message_readers", joinColumns = {@JoinColumn(name = "message_id")}, inverseJoinColumns = {@JoinColumn(name = "member_id")})
    private Set<Member> readers = new HashSet<>();
}
