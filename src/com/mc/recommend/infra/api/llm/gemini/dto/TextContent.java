package com.mc.recommend.infra.api.llm.gemini.dto;

import java.util.ArrayList;
import java.util.List;

public class TextContent {
    private List<TextPart> parts = new ArrayList<>();

    public TextContent(List<TextPart> parts) {
        this.parts = parts;
    }

    public List<TextPart> getParts() {
        return parts;
    }

    @Override
    public String toString() {
        return "TextContent{" +
                "parts=" + parts +
                '}';
    }
}
