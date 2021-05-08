package com.intern.translator.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserTranslation {

    private String wordLanguage;
    private String word;

    private String matchingWordLanguage;
    private String matchingWord;

    public UserTranslation(String wordLanguage, String word, String matchingWordLanguage, String matchingWord) {
        this.wordLanguage = wordLanguage;
        this.word = word;
        this.matchingWordLanguage = matchingWordLanguage;
        this.matchingWord = matchingWord;
    }
}
