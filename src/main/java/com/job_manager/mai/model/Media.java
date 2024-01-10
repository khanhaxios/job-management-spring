package com.job_manager.mai.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "medias")
public class Media {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String mediaLink;

    private String senderId;

    private LocalDateTime sentAt;

    private String mediaName;
    private String mediaSize;

}
