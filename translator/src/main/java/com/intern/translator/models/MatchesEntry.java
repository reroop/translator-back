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
public class MatchesEntry {

    @Id
    @GeneratedValue
    private Long match_id;

    private Long word_id;
    private String word_language;

    private Long matching_word_id;
    private String matching_word_language;

    public MatchesEntry(Long word_id, String word_language, Long matching_word_id, String matching_word_language) {
        this.word_id = word_id;
        this.word_language = word_language;
        this.matching_word_id = matching_word_id;
        this.matching_word_language = matching_word_language;
    }
}
