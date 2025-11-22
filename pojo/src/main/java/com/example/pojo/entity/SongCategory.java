package com.example.pojo.entity;

import lombok.Data;
/*
multi-to-multi relationship table between songs and categories
*/
@Data
public class SongCategory {
    private Long songId;
    private Long categoryId;
}
