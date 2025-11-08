package com.example.pojo.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
@Data
public class SongLabel implements Serializable {
    private Long id;
    private String name;
    private int isDeleted;
    private Long createdUserId;
    private LocalDate createdDate;
    private LocalDate updatedDate; // TODO Migrate to LocalDateTime
}