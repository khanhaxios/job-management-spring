package com.job_manager.mai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
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
    private String media;

    private LocalDateTime sentAt;

    public Message() {
        this.id = UUID.randomUUID().toString();
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Room room;
}
