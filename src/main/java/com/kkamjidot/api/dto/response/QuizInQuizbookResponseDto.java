package com.kkamjidot.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "문제집 상세 내용 안에 들어가는 문제 모음 응답 Dto")
public class QuizInQuizbookResponseDto {
    @Schema(description = "문제 아이디", example = "1", required = true)
    private Long quizId;

    @Schema(description = "문제 제목", example = "문제1 제목", required = true)
    private String quizTitle;

    @Schema(description = "문제 유형", example = "BASIC", required = true, defaultValue = "BASIC")
    private String quizCategory;

    @Schema(description = "풀었는지 여부", example = "true", required = true)
    private Boolean quizIsSolved;
}
