package com.intern.translator.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TranslationEntry {

    @Id
    @GeneratedValue
    private Long id;

    private String language;
    private String word;

    public TranslationEntry(String word, String language) {
        this.word = word;
        this.language = language;
    }
}
