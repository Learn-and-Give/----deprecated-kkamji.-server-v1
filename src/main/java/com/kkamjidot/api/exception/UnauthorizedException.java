package com.kkamjidot.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 인증이 필요한 경우, 혹은 잘못된 인증
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("존재하지 않는 회원입니다.");
    }
}
