package com.example.server.mapper;

import com.example.pojo.entity.Song;
import com.example.server.annotation.AutoFill;
import enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;

@Mapper
public interface SongMapper {

    /**
     * insert song
     *
     * @param song
     */
    @Insert("insert into song (name, artist, description, created_user_id, is_archived, is_deleted, is_private, created_date, updated_date) " +
            "values (#{name}, #{artist}, #{description}, #{createdUserId}, #{isArchived}, #{isDeleted}, #{isPrivate}, #{createdDate}, #{updatedDate})")
    @AutoFill(value = OperationType.INSERT)
    int insert(Song song);

    @Update("update song set is_deleted = 1, updated_date = #{updatedDate} where id = #{id}")
    void deletebyID(Long id, LocalDate updatedDate);
     @Update("update song set name = #{name}, artist = #{artist}, description = #{description}, is_archived = #{isArchived}, is_private = #{isPrivate}, updated_date = #{updatedDate} where id = #{id}")
     @AutoFill(value = OperationType.UPDATE)
     void update(Song song);

    @Select("select from song where id=#{id}")
    Song selectById(Long id);

    /**
     * list songs by category id
     *
     * @param category_id
     * @return
     */
    @Select("select * from song where category_id=#{category_id} order by created_date desc")
    Object listByCategory(Long category_id);
}
