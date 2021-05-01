package com.intern.translator.repositories;

import com.intern.translator.models.TranslationEntry;
import com.intern.translator.models.TranslationMatches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslatorRepository extends JpaRepository<TranslationEntry, Long> {

    //List<String> findWordExactMatchesLikeIgnoreCase(String word);
}
