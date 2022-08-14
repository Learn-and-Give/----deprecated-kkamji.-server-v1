package com.kkamjidot.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Schema(name = "문제 풀기 요청 Dto")
@NoArgsConstructor
@Getter
public class SolveQuizRequestDto {
    @Schema(description = "정답/오답", name = "solveIsCorrect", title = "isCorrect", example = "true", required = true)
    @NotNull(message = "필수 입력 값입니다.")
    private Boolean solveIsCorrect;
}
