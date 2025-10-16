package com.example.server.mapper;

import com.example.pojo.DTO.UserPageQueryDTO;
import com.example.pojo.entity.User;
import com.example.result.PageResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface  UserMapper {
    /**
     * insert user
     * @param user
     */
    @Insert("insert into users (username, password, email, createdDate, updatedDate) " +
            "values (#{username}, #{password}, #{email}, #{createdDate}, #{updatedDate})")
    void insert(User user);

    @Select("select * from users where username = #{username}")
    User selectByUsername(String username);

    @Select("select * from users limit #{pageSize} offset #{pageNum}")
    PageResult selectByPage(UserPageQueryDTO userPageQueryDTO);
}
