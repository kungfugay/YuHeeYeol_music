package com.mc.recommend.coordinator;

import com.mc.recommend.domain.song.SongMessage;
import com.mc.recommend.infra.api.llm.BaseChatModel;
import com.mc.recommend.infra.api.llm.BaseRequest;
import com.mc.recommend.infra.api.llm.BaseResponse;
import com.mc.recommend.infra.api.llm.ResponseFormat;
import com.mc.recommend.infra.api.llm.gemini.GeminiChatModel;
import com.mc.recommend.infra.api.llm.gemini.TextRequest;
import com.mc.recommend.qr.QRCodeGenerator;

public class Coordinator {
    public SongMessage coordinate(String msg) {
        // String msg = "우울해";
        msg += ", 노래 1곡만 추천해줘. 추천 이유도 알려줘.";

        // 추상화
        // GeminiChatModel model = GeminiChatModel.getInstance();
        BaseChatModel model = GeminiChatModel.getInstance();
        BaseRequest request = new TextRequest(msg, new ResponseFormat<SongMessage>(SongMessage.class));
        BaseResponse response = model.invoke(request);
        SongMessage song = response.response(SongMessage.class);

        QRCodeGenerator qrcode = new QRCodeGenerator();
        qrcode.generate(song);
        return song;
    }
}
