package com.kkamjidot.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(name = "의견 응답 Dto")
public class CreateQuizResponseDto {
    @Schema(description = "문제 아이디", example = "1", required = true)
    private final Long quizId;
}
