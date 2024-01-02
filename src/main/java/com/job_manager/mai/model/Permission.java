package com.job_manager.mai.model;

import jakarta.persistence.*;
import lombok.Data;
import org.yaml.snakeyaml.events.Event;

@Entity
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String description;

    @Column(nullable = false)
    private String name;

}
