package com.example.pojo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "practice_time_record")
public class PracticeTimeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String musicId;
    private Integer bpm;
    private Long duration; // 秒数
    private LocalDate date;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
