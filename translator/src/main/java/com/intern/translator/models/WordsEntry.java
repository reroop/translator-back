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
public class WordsEntry {

    @Id
    @GeneratedValue
    private Long word_id;

    private String language;
    private String word;

    public WordsEntry(String language, String word) {
        this.language=language;
        this.word=word;
    }
}
