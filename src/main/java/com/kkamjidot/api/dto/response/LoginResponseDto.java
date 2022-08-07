package com.kkamjidot.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(name = "로그인 응답 Dto")
public class LoginResponseDto {
    @Schema(description = "회원 ID", example = "1", required = true)
    private final Long userId;

    @Schema(description = "이름", example = "홍길동", required = true)
    private final String name;
}
