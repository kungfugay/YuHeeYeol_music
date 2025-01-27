package com.mc.recommend.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
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

        String url = "https://nonoki.com/music/search/";
        String music = songMessage.getSinger() == null ? "" : songMessage.getSinger() + "-";
        music += songMessage.getTitle();

        String encoded = URLEncoder.encode(url + music, StandardCharsets.UTF_8); // 한글이 들어올 수도 있으니
        encoded = encoded
                .replace("\\+", "_")
                .replace("%2F", "/")
                .replace("%3A", ":");

        String fileName = music + ".png";
        
        try {
            BitMatrix matrix = qrCodeWriter.encode(encoded, BarcodeFormat.QR_CODE, 100, 100);
            MatrixToImageWriter.writeToStream(matrix, "png", new FileOutputStream(fileName));
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
