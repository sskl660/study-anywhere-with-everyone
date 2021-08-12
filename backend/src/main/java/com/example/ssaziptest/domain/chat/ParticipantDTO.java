package com.example.ssaziptest.domain.chat;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ParticipantDTO {
    private String partEmail;
    private String partTerm;
    private String partName;
    private LocalDate enterTime;
}
