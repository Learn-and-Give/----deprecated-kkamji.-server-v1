package com.kkamjidot.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public class QuizInQuizbookResponseDto {
    @Schema(description = "문제 제목", example = "문제1 제목", required = true)
    private String quizTitle;

    @Schema(description = "문제 유형", example = "BASIC", required = true, defaultValue = "BASIC")
    private String quizCategory;

    @Schema(description = "풀었는지 여부", example = "true", required = true)
    private Boolean quizIsSolved;
}
