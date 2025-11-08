package com.example.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Song implements Serializable {
    private Long id;
    private String name;
    private String artist;
    private String description;
    private int isDeleted;
    private int isArchived;
    private Long createdUserId;
    private LocalDate createdDate;
    private LocalDate updatedDate; // TODO Migrate to LocalDateTime
}