package com.example.ssaziptest.domain.feed;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GalaxyExitRequest {
    private String userEmail;
    private String startTime;
}
