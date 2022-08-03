package com.kkamjidot.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(name = "퀴즈 풀기 응답 Dto")
public class SolveQuizResponseDto {
    @Schema(description = "회원 아이디", example = "1", required = true)
    private Long userId;

    @Schema(description = "회원 이름", example = "홍길동", required = true)
    private String userName;

    @Schema(description = "문제 아이디", example = "1", required = true)
    private Long quizId;

    @Schema(description = "문제 정답 여부", example = "true", required = true)
    private Boolean solveIsCorrect;
}
