package com.mc.recommend.infra.error.code;

public enum ErrorCode {
    NOT_VALID_GEMINI_RESPONSE("AI가 대답하기 곤란해 해요. 다시 질문 해주시겠어요?"),
    FAILED_CONNECT("AI가 아파요.");

    public final String msg;

    ErrorCode(String msg) {
        this.msg = msg;
    }
}
