package com.kkamjidot.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CommonExceptionHandler {
    // 인증이 필요한 경우, 혹은 잘못된 인증
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUnauthorizedException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));     // 401 UNAUTHORIZED
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));     // 404 DATA NOT FOUND
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity handleUnauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", e.getMessage()));     // 403 FORBIDDEN
    }

    @ExceptionHandler(KeyAlreadyExistsException.class)
    public ResponseEntity handleKeyAlreadyExistsException(KeyAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));     // 409 CONFLICT
    }
}
