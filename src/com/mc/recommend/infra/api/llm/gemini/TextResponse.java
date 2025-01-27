package com.mc.recommend.infra.api.llm.gemini;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mc.recommend.infra.api.llm.BaseResponse;
import com.mc.recommend.infra.api.llm.gemini.dto.TextResponseDocument;
import com.mc.recommend.infra.error.CommonException;
import com.mc.recommend.infra.error.code.ErrorCode;

public class TextResponse implements BaseResponse {
    private String responseText;

    public TextResponse(String responseText) {
        this.responseText = responseText;
    }

    @Override
    public <T> T response(Class<T> target) {

        try {
            Gson gson = new Gson();
            TextResponseDocument response = gson.fromJson(responseText, TextResponseDocument.class);
            String text = response
                    .getCandidates()
                    .get(0)
                    .getContent()
                    .getParts()
                    .get(0)
                    .getText();
            // getFirst() = get(0)
            return gson.fromJson(text, target);
        } catch (JsonSyntaxException e){
            throw new CommonException(ErrorCode.NOT_VALID_GEMINI_RESPONSE);
        }
    }
}
