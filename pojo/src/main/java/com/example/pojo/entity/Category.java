package com.example.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/*
"Category" function is owned and created by each user.
"Tag" function is public to all users.

Service Requirements:
1. Each user can create their own categories.
2. Categories can be named and described.
3. Categories can be marked as private or public.
4. Categories can be archived or deleted.
5. Categories can be shared with other users.
*/

@Data
public class Category implements Serializable {
    private Long id;
    private String name;
    private String description;
    private int isDeleted;
    private int isArchived;
    private int isPrivate;
    private Long createdUserId;
    private LocalDate createdDate;
    private LocalDate updatedDate; // TODO Migrate to LocalDateTime
}