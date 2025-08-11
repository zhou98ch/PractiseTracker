package com.example.pojo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeTimeRecord {
    private Long id;
    private String userId;
    private String musicId;
    private Integer bpm;
    private Long duration; // counted in seconds
    private LocalDate date;
    private LocalDateTime updatedAt;
}