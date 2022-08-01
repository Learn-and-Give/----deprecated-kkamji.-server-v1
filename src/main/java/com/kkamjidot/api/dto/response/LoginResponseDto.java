package com.kkamjidot.api.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    @ApiModelProperty(value = "회원 ID", example = "1", required = true)
    private final Long userId;

    @ApiModelProperty(value = "이름", example = "홍길동", required = true)
    private final String name;
}
