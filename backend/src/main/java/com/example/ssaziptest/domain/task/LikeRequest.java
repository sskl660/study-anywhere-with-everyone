package com.example.ssaziptest.domain.task;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeRequest {
    private int taskNo;
    private String userEmail;
}
