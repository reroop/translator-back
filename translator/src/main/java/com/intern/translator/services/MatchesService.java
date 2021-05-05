package com.intern.translator.services;

import com.intern.translator.models.MatchesEntry;
import com.intern.translator.models.WordsEntry;
import com.intern.translator.repositories.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchesService {

    @Autowired
    private MatchesRepository matchesRepository;

    @Autowired
    private WordsService wordsService;

    public List<WordsEntry> findAllEntriesWithWord(String word, String language, String translationLanguage) {
        Long wordId = wordsService.findWordId(word, language).getWord_id();

        List<Long> translationsWordIDs = new ArrayList<>();
        //List<MatchesEntry> resultLanguageToTranslation = ;
        for (MatchesEntry m1: matchesRepository.findMatchingWordsByWordIdAndMatchingLanguage(wordId, translationLanguage)) {
            translationsWordIDs.add(m1.getMatching_word_id());
        }

        for (MatchesEntry m2: matchesRepository.findMatchingWordsByMatchingWordIdAndWordLanguage(wordId, translationLanguage)) {
            translationsWordIDs.add(m2.getWord_id());
        }

        List<WordsEntry> result = new ArrayList<>();

        for (Long id: translationsWordIDs) {
            result.add(wordsService.findById(id));
        }

        return result;
    }

    @CrossOrigin
    @PostMapping
    public MatchesEntry saveMatchesEntry(@RequestBody MatchesEntry matchesEntry) {
        return matchesRepository.save(matchesEntry);
    }
}
