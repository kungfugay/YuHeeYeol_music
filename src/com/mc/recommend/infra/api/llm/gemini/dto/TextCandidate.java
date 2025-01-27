package com.mc.recommend.infra.api.llm.gemini.dto;

public class TextCandidate {
    private TextContent content;    // json이름 따라가야함

    public TextCandidate(TextContent content) {
        this.content = content;
    }

    public TextContent getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "TextCandidate{" +
                "content=" + content +
                '}';
    }
}
