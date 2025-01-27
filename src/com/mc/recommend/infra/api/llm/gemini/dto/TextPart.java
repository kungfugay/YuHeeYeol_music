package com.mc.recommend.infra.api.llm.gemini.dto;

public class TextPart {
    private String text;

    public TextPart(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "TextPart{" +
                "text='" + text + '\'' +
                '}';
    }
}
