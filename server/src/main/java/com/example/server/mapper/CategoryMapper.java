package com.example.server.mapper;

import com.example.pojo.entity.Category;
import com.example.pojo.entity.Song;
import com.example.server.annotation.AutoFill;
import enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * insert song
     *
     * @param song
     */
    @Insert("insert into category (name, description, is_archived, is_deleted, is_private, created_user_id, created_date, updated_date) " +
            "values (#{name}, #{description}, #{isArchived}, #{isDeleted}, #{isPrivate}, #{createdUserId}, #{createdDate}, #{updatedDate})")
    @AutoFill(value = OperationType.INSERT)
    int insert(Category song);

    @Update("update category set is_deleted = 1, updated_date = #{updatedDate} where id = #{id}")
    void deletebyID(Long id, LocalDate updatedDate);
     @Update("update category set name = #{name}, description = #{description}, is_archived = #{isArchived}, is_private = #{isPrivate}, updated_date = #{updatedDate} where id = #{id}")
     @AutoFill(value = OperationType.UPDATE)
     void update(Category song);

    @Select("select from category where id=#{id}")
    Category selectById(Long id);

    /**
     * select all categories
     *
     * @return
     */
    @Select("select from category where is_deleted = 0 and created_user_id = #{userId}")
    List<Category> selectAll(Long userId);
}
