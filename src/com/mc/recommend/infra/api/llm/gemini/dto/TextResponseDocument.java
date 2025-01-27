package com.mc.recommend.infra.api.llm.gemini.dto;

import java.util.List;

public class TextResponseDocument {
    private List<TextCandidate> candidates;

    public TextResponseDocument(List<TextCandidate> candidates) {
        this.candidates = candidates;
    }

    public List<TextCandidate> getCandidates() {
        return candidates;
    }

    @Override
    public String toString() {
        return "TextResponseDocument{" +
                "candidates=" + candidates +
                '}';
    }
}
