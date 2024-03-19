package com.fastcampus.sns.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "User name is duplicated"),;

    // 어떤 에러이느냐에 따라서 http status를 변경해줄 것이다.
    private HttpStatus status;
    private String message;
}
