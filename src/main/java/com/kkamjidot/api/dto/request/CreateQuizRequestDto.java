package com.kkamjidot.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Schema(name = "문제 생성 요청 Dto")
@NoArgsConstructor
@Getter
public class CreateQuizRequestDto {
    @Schema(description = "문제 제목", example = "문제 제목", required = true)
    @NotNull(message = "문제 제목은 필수 입력 값입니다.")
    private String quizTitle;

    @Schema(description = "문제 내용", example = "문제 내용", required = true)
    @NotNull(message = "문제 내용은 필수 입력 값입니다.")
    private String quizContent;

    @Schema(description = "문제 정답", example = "문제 정답", required = true)
    @NotNull(message = "문제 정답은 필수 입력 값입니다.")
    private String quizAnswer;

    @Schema(description = "문제 해설", example = "문제 해설", required = true)
    @NotNull(message = "문제 해설은 필수 입력 값입니다.")
    private String quizExplanation;

    @Schema(description = "문제 출처", example = "문제 출처", required = true)
    @NotNull(message = "문제 출처는 필수 입력 값입니다.")
    private String quizSource;
}
