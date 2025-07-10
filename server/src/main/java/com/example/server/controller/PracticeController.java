package com.example.server.controller;

import com.example.pojo.DTO.PracticeTimeRequestDTO;
import com.example.pojo.entity.PracticeTimeRecord;
import com.example.server.repository.PracticeTimeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/practice")
public class PracticeController {

    private final PracticeTimeRecordRepository practiceRepository;

    @Autowired
    public PracticeController(PracticeTimeRecordRepository practiceRepository) {
        this.practiceRepository = practiceRepository;
    }

    @PostMapping
    public PracticeTimeRecord savePracticeRecord(@RequestBody PracticeTimeRequestDTO practiceTimeRequestDTO) {
        PracticeTimeRecord record = new PracticeTimeRecord();
        // Convert DTO to Entity
        record.setMusicId(practiceTimeRequestDTO.getMusicId());
        record.setBpm(practiceTimeRequestDTO.getBpm());
        record.setDuration(practiceTimeRequestDTO.getDuration());
        record.setDate(LocalDate.now());
        record.setUserId("current_user_id"); // TODO: Replace with actual user ID from security context

        return practiceRepository.save(record);
    }

    @GetMapping
    public List<PracticeTimeRecord> getAllPracticeRecords() {
        return practiceRepository.findAll();
    }
}
