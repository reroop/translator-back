package com.intern.translator.repositories;

import com.intern.translator.models.WordsEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordsRepository extends JpaRepository<WordsEntry, Long> {
    //table structure:    (automatic) word_id     |   language    |   word

    @Query("SELECT w FROM WordsEntry w WHERE w.language='estonian' AND w.word=:word")
    List<WordsEntry> findByWordInEstonian(@Param("word") String word);

    @Query("SELECT w From WordsEntry w WHERE w.language='estonian'")
    List<WordsEntry> findAllEstonianWords();

    @Query("SELECT w FROM WordsEntry w where w.language=:language AND w.word=:word")
    WordsEntry findWordId(@Param("word") String word, @Param("language") String language);//todo: ei t66ta

    @Query("select w From WordsEntry w")
    List<WordsEntry> getAllWords();
}
