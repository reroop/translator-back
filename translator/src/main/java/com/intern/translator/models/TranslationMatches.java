package com.intern.translator.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TranslationMatches {
    private String language;
    private List<String> matchedWords;

    public TranslationMatches(String language, List<String> words) {
        this.language = language;
        this.matchedWords = words;
    }
}
