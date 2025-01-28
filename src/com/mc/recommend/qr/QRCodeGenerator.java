package com.mc.recommend.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mc.recommend.domain.song.SongMessage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class QRCodeGenerator {
    public void generate(SongMessage songMessage) {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        // 들어갈 정보
        String url = "https://nonoki.com/music/search/";
        String musicInfo = songMessage.getSinger() == null ? "" : songMessage.getSinger() + "-" + songMessage.getTitle(); // 가수명 - 곡명

        // UTF-8 포맷팅: url String에 한글이 들어올 수도 있음
        String encoded = URLEncoder.encode(url + musicInfo, StandardCharsets.UTF_8);
        encoded = encoded
                .replace("\\+", "_")
                .replace("%2F", "/")
                .replace("%3A", ":");

        // QR코드 이미지 저장명
        String fileName = musicInfo + ".png";
        

        try {
            // 매트릭스 생성
            BitMatrix matrix = qrCodeWriter.encode(encoded, BarcodeFormat.QR_CODE, 100, 100);

            // 매트릭스에 색상 추가
            String colorStr = songMessage.getColor();
            int hexColor = strToHex(colorStr);
            MatrixToImageConfig colorConfig = new MatrixToImageConfig(MatrixToImageConfig.BLACK, hexColor);

            // 매트릭스를 이미지로 변환
            MatrixToImageWriter.writeToStream(matrix, "png", new FileOutputStream(fileName), colorConfig);

        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int strToHex(String str) {

        int alpha = 255; // 0xff 디폴트값 사용
        int red = Integer.parseInt(str.substring(1, 3), 16);
        int green = Integer.parseInt(str.substring(3, 5), 16);
        int blue = Integer.parseInt(str.substring(5, 7), 16);

        return alpha << 24 | red << 16 | green << 8 | blue; // 비트쉬프트 연산
    }
}
