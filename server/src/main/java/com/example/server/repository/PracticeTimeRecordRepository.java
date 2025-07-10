package com.example.server.repository;

import com.example.pojo.entity.PracticeTimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticeTimeRecordRepository extends JpaRepository<PracticeTimeRecord, Long> {
}
