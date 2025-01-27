package com.mc.recommend.infra.api.llm.gemini.dto;

import java.util.List;

public class TextRequestDocument {
    private List<TextContent> contents;

    public TextRequestDocument(List<TextContent> contents) {
        this.contents = contents;
    }

    public List<TextContent> getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "TextRequestDocument{" +
                "contents=" + contents +
                '}';
    }
}
