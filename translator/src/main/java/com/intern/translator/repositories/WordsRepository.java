package com.intern.translator.repositories;

import com.intern.translator.models.WordsEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordsRepository extends JpaRepository<WordsEntry, Long> {

    @Query("SELECT w FROM WordsEntry w WHERE w.language='estonian' AND w.word=LOWER(CONCAT('%',:word, '%'))")
    List<WordsEntry> findByWordInEstonian(@Param("word") String word);

    @Query("SELECT w From WordsEntry w WHERE w.language='estonian'")
    List<WordsEntry> findAllEstonianWords();


}
