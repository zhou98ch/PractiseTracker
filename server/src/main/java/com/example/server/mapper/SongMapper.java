package com.example.server.mapper;

import com.example.pojo.entity.Song;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SongMapper {

    /**
     * insert song
     *
     * @param song
     */
    @Insert("insert into song (name, artist, description, created_user_id, is_archived, is_deleted, is_private, created_date, updated_date) " +
            "values (#{name}, #{artist}, #{description}, #{createdUserId}, #{isArchived}, #{isDeleted}, #{isPrivate}, #{createdDate}, #{updatedDate})")
    int insert(Song song);
}
