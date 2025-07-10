package com.example.pojo.DTO;

import lombok.Data;
import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticeTimeRequestDTO implements Serializable {
    private String musicId;
    private int bpm;
    private Long duration; // unit: second
}
