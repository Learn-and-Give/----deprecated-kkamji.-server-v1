package com.kkamjidot.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

//DTO 클래스를 사용하여 요청 데이터 정의
@Schema(name = "로그인 요청 데이터")
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginRequestDto {
    @Schema(description = "이름", example = "홍길동", required = true)
    @NotNull(message = "이름은 필수 입력 값입니다.")
    private String name;

    @Schema(description = "비밀번호", example = "1234", required = true)
    @NotNull(message = "비밀번호는 필수 입력 값입니다.")
    private String code;
}
