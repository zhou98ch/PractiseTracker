package com.example.server.mapper;

import com.example.pojo.DTO.UserPageQueryDTO;
import com.example.pojo.entity.User;
import com.example.server.annotation.AutoFill;
import enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface  UserMapper {
    /**
     * insert user
     * @param user
     */
    @Insert("insert into users (username, password, email, createdDate, updatedDate) " +
            "values (#{username}, #{password}, #{email}, #{createdDate}, #{updatedDate})")
    @AutoFill(value = OperationType.INSERT)
    void insert(User user);

    @Select("select * from users where username = #{username}")
    User selectByUsername(String username);

    List<User> selectByPage(UserPageQueryDTO userPageQueryDTO);

    @Select("select * from users where id = #{id}")
    User selectById(Long id);
}
