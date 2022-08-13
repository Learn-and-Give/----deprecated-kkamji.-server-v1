package com.kkamjidot.api.dto.response;

import com.kkamjidot.api.domain.Solve;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Builder
@Schema(name = "퀴즈 풀기 응답 Dto")
public class SolveQuizResponseDto {
    @Schema(description = "풀기 아이디", example = "1", required = true)
    private Long solveId;

//    @Schema(description = "문제 정답 여부", example = "true", required = true)
//    private Boolean solveIsCorrect;

    public static SolveQuizResponseDto of(Solve solve) {
        return SolveQuizResponseDto.builder()
                .solveId(solve.getId())
//                .solveIsCorrect(solve.getSolveIsCorrect())
                .build();
    }
}
