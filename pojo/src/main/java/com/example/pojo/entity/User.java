package com.example.pojo.entity;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDate createdDate;
    private LocalDate updatedDate;
}