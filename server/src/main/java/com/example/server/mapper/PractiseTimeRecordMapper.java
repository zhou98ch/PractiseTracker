package com.example.server.mapper;


//import com.github.pagehelper.Page;
//import com.sky.annotation.AutoFill;
//import com.sky.dto.EmployeePageQueryDTO;
//import com.sky.dto.PasswordEditDTO;
//import com.sky.entity.Employee;
//import com.sky.enumeration.OperationType;
import com.example.pojo.entity.PracticeTimeRecord;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PractiseTimeRecordMapper {
    /**
     * insert practise time record
     * @param practiseTimeRecord
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into practice_time_record (user_id, music_id, bpm, date, duration, updated_at) " +
            "values (#{userId}, #{musicId}, #{bpm}, #{date}, #{duration}, #{updatedAt})")
    void insert(PracticeTimeRecord practiseTimeRecord);

    @Select("SELECT * FROM practice_time_record WHERE id = #{id}")
    PracticeTimeRecord findById(Long id);

    @Update("UPDATE practice_time_record SET duration = #{duration}, updated_at = #{updatedAt} WHERE id = #{id}")
    void updateDuration(PracticeTimeRecord record);
}
