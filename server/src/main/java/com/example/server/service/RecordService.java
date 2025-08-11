package com.example.server.service;

import com.example.pojo.entity.PracticeTimeRecord;
import com.example.server.mapper.PractiseTimeRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final PractiseTimeRecordMapper recordMapper;

    public PracticeTimeRecord createRecord(PracticeTimeRecord record) {
        record.setUpdatedAt(LocalDateTime.now());
        recordMapper.insert(record);
        return record;
    }
    

}
