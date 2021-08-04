package com.example.ssaziptest.domain.rank;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RankResponse {
    private String userEmail;
    private String userName;
    private int week;

    @Builder
    public RankResponse(String userEmail, String userName, int week) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.week = week;
    }
}
