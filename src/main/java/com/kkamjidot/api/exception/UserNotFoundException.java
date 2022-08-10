package com.kkamjidot.api.exception;

// 인증이 필요한 경우, 혹은 잘못된 인증
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("존재하지 않는 회원입니다.");
    }
}
