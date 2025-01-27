package com.mc.recommend.domain.song;

// 캡슐화됨 (setter, 생성자 없음) but private 제어자를 무시하고 값을 넣고 싶음.
public class SongMessage {
    private String singer;
    private String title;
    private String reason;

    public String getSinger() {
        return singer;
    }

    public String getTitle() {
        return title;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "SongMessage{" +
                "singer='" + singer + '\'' +
                ", title='" + title + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
