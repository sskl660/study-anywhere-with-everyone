package com.example.ssaziptest.domain.feed;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GalaxyEntryRequest {
    private String userEmail;
    private String message;
}
