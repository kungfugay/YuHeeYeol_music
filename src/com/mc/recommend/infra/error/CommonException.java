package com.mc.recommend.infra.error;

import com.mc.recommend.infra.error.code.ErrorCode;

public class CommonException extends RuntimeException {
    private ErrorCode errorCode;

    public CommonException(ErrorCode errorCode) {
        super(errorCode.msg);
        this.errorCode = errorCode;
    }

}
