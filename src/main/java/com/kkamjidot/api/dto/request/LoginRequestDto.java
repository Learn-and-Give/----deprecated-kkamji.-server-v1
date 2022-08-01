package com.kkamjidot.api.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

//DTO 클래스를 사용하여 요청 데이터 정의
@ApiModel(value = "로그인 요청 데이터")
@Getter
public class LoginRequestDto {
    @ApiModelProperty(value = "이름", example = "홍길동", required = true)
    private String name;

    @ApiModelProperty(value = "비밀번호", example = "1234", required = true)
    private String password;
}
