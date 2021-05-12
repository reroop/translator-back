package com.intern.translator.repositories;

import com.intern.translator.models.WordsEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordsRepository extends JpaRepository<WordsEntry, Long> {
    //table structure:    (automatic) word_id     |   language    |   word

    @Query("SELECT w FROM WordsEntry w WHERE w.language=:language AND w.word=:word")
    WordsEntry findWordId(@Param("word") String word, @Param("language") String language);

    @Query("SELECT w from WordsEntry w WHERE w.language=:language")
    List<WordsEntry> findAllLanguageWords(@Param("language") String language);

    @Query ("SELECT DISTINCT w.language FROM WordsEntry w")
    List<String> getAllSavedLanguages();
}
