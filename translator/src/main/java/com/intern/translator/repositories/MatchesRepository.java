package com.intern.translator.repositories;

import com.intern.translator.models.MatchesEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchesRepository extends JpaRepository<MatchesEntry, Long> {
    //table structure:    (automatic) match_id  |  matching_word_id  | matching_word_language |  word_id | word_language

    @Query("SELECT m From MatchesEntry m WHERE m.word_id=:word_id " +
            "AND m.matching_word_language=:matching_word_language")
    List<MatchesEntry> findMatchingWordsByWordIdAndMatchingLanguage(@Param("word_id") Long word_id,
                                                                    @Param("matching_word_language") String matching_word_language);


    @Query("SELECT m From MatchesEntry m WHERE m.matching_word_id=:matching_word_id " +
            "AND m.word_language=:word_language")
    List<MatchesEntry> findMatchingWordsByMatchingWordIdAndWordLanguage(@Param("matching_word_id") Long matching_word_id,
                                                                        @Param("word_language") String word_language);

}
