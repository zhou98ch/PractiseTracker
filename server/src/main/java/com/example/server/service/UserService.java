package com.example.server.service;

import com.example.pojo.DTO.UserDTO;
import com.example.pojo.DTO.UserLoginDTO;
import com.example.pojo.entity.User;
import com.example.server.mapper.UserMapper;
import constant.MessageConstant;
import exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void save(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()));
        user.setCreatedDate(LocalDate.now());
        user.setUpdatedDate(LocalDate.now());
        userMapper.insert(user);
    }

    public User login(UserLoginDTO userLoginDTO){
        User user = userMapper.selectByUsername(userLoginDTO.getUsername());
        if(user==null){
            throw new BaseException(MessageConstant.USER_NOT_FOUND);
        }
        if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(userLoginDTO.getPassword().getBytes()))){
            throw new BaseException(MessageConstant.PASSWORD_ERROR);
        }

        return user;
    }
}
