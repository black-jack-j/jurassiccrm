package com.jurassic.jurassiccrm.logging.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String action;
    @Column(nullable = false)
    private LocalDateTime timestamp;
}
