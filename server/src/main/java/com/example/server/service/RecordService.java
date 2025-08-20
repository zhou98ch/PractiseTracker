package com.example.server.service;

import com.example.pojo.entity.PracticeTimeRecord;
import com.example.server.mapper.PractiseTimeRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final PractiseTimeRecordMapper recordMapper;

    public PracticeTimeRecord createRecord(PracticeTimeRecord record) {
        PracticeTimeRecord exsitingRecord = recordMapper.findByUniqueRecord(record.getUserId(), record.getMusicId(), record.getBpm(), record.getDate());
        if(exsitingRecord!= null){
            recordMapper.updateDuration(exsitingRecord.getId(), record.getDuration(), record.getUpdatedAt());
        }
        else{
            recordMapper.insert(record);
        }
        return record;
    }

}
