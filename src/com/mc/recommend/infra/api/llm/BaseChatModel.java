package com.mc.recommend.infra.api.llm;

public interface BaseChatModel {
    BaseResponse invoke(BaseRequest req);
}
