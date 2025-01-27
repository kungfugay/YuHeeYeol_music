package com.mc.recommend.infra.api.llm.gemini;

import com.google.gson.Gson;
import com.mc.recommend.infra.api.llm.BaseChatModel;
import com.mc.recommend.infra.api.llm.BaseRequest;
import com.mc.recommend.infra.api.llm.BaseResponse;
import com.mc.recommend.infra.api.llm.gemini.dto.TextResponseDocument;
import com.mc.recommend.infra.error.CommonException;
import com.mc.recommend.infra.error.code.ErrorCode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class GeminiChatModel implements BaseChatModel {
    private final String API_URI = "https://generativelanguage.googleapis.com" +
            "/v1beta" +
            "/models" +
            "/gemini-1.5-flash:generateContent";    // ?key= 이하는 나중에 결합
    private final String API_KEY = "";
    private HttpClient httpClient;  // 바뀌지 않을 것임

    // 싱글톤
    // 바뀌어서는 안되고, 매번 같은 객체를 써도 되는 상황
    private static GeminiChatModel INSTANCE;
    public static GeminiChatModel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GeminiChatModel();
        }
        return INSTANCE;
    }

    private GeminiChatModel() {
        // HttpClient 인스턴스 초기화
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    @Override
    public BaseResponse invoke(BaseRequest req) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URI + "?key=" + API_KEY))
                    .timeout(Duration.ofMinutes(2))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(req.toJson()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonText = response.body();
            //Gson gson = new Gson();
            //TextResponseDocument res  = gson.fromJson(jsonText, TextResponseDocument.class);
            return new TextResponse(jsonText);

        } catch (IOException | InterruptedException e) {
            //throw new RuntimeException(e);
            throw new CommonException(ErrorCode.FAILED_CONNECT);
        }
    }
}