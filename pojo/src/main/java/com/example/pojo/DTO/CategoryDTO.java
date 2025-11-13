package com.example.pojo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private int isPrivate;
    private Long createdUserId;
}
