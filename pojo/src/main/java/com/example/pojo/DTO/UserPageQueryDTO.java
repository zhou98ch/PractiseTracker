package com.example.pojo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPageQueryDTO implements Serializable {
    private String username;

    private int page;

    private int pageSize;

}
