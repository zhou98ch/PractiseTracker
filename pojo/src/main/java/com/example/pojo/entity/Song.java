package com.example.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/*
Service Requirements:
1. Each user can create their own songs.
2. Songs can be named and described.
3. Songs can be marked as private or public.
4. Songs can be archived or deleted.
5. Songs can be shared with other users.
6. A song can belong to zero or more categories.
*/
@Data
public class Song implements Serializable {
    private Long id;
//    private Long categoryId; // move to song_category table, multi-to-multi relationship
    private String name;
    private String artist;
    private String description;
    private int isDeleted;
    private int isArchived;
    private int isPrivate;
    private Long createdUserId;
    private LocalDate createdDate;
    private LocalDate updatedDate; // TODO Migrate to LocalDateTime
}