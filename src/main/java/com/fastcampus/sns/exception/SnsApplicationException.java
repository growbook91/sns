package com.fastcampus.sns.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

// TODO : implement
@AllArgsConstructor
@Getter
public class SnsApplicationException extends RuntimeException{// 이 extends 클릭하고 command N 누르면 관련된 것 생성 가능
    // 이제는 exception에 대한 처리
    // 이 에러코드는 어플에서 일어나는 모든 exception에 대해서 snsApplicationException을 던지고 있는데
    // 어떤 에러인지를 명시하기 위함.
    private ErrorCode errorCode;
    private String message;

    public SnsApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message == null){
            return errorCode.getMessage();
        }
        return String.format("%s. %s", errorCode.getMessage(), message);
    }
}
