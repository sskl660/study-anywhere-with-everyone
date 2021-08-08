package com.example.ssaziptest.domain.task;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskLikeCheckResponse {
        private boolean userLikeFlag;

        @Builder
        public  TaskLikeCheckResponse(boolean userLikeFlag) {
                this.userLikeFlag = userLikeFlag;
        }
}
