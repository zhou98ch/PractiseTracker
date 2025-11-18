package com.example.server.service;

import com.example.pojo.DTO.UserDTO;
import com.example.pojo.DTO.UserLoginDTO;
import com.example.pojo.DTO.UserPageQueryDTO;
import com.example.pojo.entity.User;
import com.example.result.PageResult;
import com.example.server.mapper.UserMapper;
import constant.MessageConstant;
import exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void save(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()));
//        user.setCreatedDate(LocalDate.now());
//        user.setUpdatedDate(LocalDate.now());
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

    public PageResult query(UserPageQueryDTO userPageQueryDTO) {
//        PageResult result = userMapper.selectByPage(userPageQueryDTO);
//        return result;
//        PageHelper.startPage(userPageQueryDTO.getPage(), userPageQueryDTO.getPageSize());
//        Page<User> pages = userMapper.selectByPage(userPageQueryDTO);
//        long total = pages.getTotal();
//        List<User> users = pages.getResult();
//        return new PageResult(total, users);
         List<User> users = userMapper.selectByPage(userPageQueryDTO);
        long total = users.size();
        return new PageResult(total, users);




    }

    public User getById(Long id) {
        User user = userMapper.selectById(id);
        if(user==null){
            throw new BaseException(MessageConstant.USER_NOT_FOUND);
        }
        return user;
    }
}
