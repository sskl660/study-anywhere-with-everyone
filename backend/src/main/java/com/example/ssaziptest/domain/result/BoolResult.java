package com.example.ssaziptest.domain.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoolResult {
    private String name;
    private boolean result = false;
    private String state = "state";
}
