package com.kkamjidot.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(name = "정답, 해설, 출처 수정 요청 Dto")
@NoArgsConstructor
@Getter
public class UpdateAnswerRequestDto {
    @Schema(description = "정답", example = "정답", required = true)
    private String quizAnswer;

    @Schema(description = "해설", example = "해설", required = true)
    private String quizExplanation;

    @Schema(description = "출처", example = "출처", required = true)
    private String quizSource;
}