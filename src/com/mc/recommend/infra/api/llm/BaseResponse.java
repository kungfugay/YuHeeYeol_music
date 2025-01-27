package com.mc.recommend.infra.api.llm;

public interface BaseResponse {
    <T> T response(Class<T> target);
}
