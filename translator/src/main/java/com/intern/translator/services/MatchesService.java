package com.intern.translator.services;

import com.intern.translator.misc.Levenshtein;
import com.intern.translator.models.MatchesEntry;
import com.intern.translator.models.WordsEntry;
import com.intern.translator.repositories.MatchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class MatchesService {

    private static final int FUZZY_MATCH_LIMITER = 2;

    @Autowired
    private MatchesRepository matchesRepository;

    @Autowired
    private WordsService wordsService;

    public List<WordsEntry> findAllEntriesWithWord(String word, String language, String translationLanguage) {

        try {
            Long wordId = wordsService.findWordId(word, language).getWord_id();
            List<Long> translationsWordIDs = new ArrayList<>();
            for (MatchesEntry m1 : matchesRepository.findMatchingWordsByWordIdAndMatchingLanguage(wordId, translationLanguage)) {
                translationsWordIDs.add(m1.getMatching_word_id());
            }

            for (MatchesEntry m2 : matchesRepository.findMatchingWordsByMatchingWordIdAndWordLanguage(wordId, translationLanguage)) {
                translationsWordIDs.add(m2.getWord_id());
            }

            List<WordsEntry> result = new ArrayList<>();

            for (Long id : translationsWordIDs) {
                result.add(wordsService.findById(id));
            }

            return result;
        } catch (Exception e) {
            String possibleFuzzyWord = findFuzzyMatchForWord(word, language, translationLanguage);
            if (possibleFuzzyWord == null) {
                return null;
            }

            if (wordsService.findWordId(possibleFuzzyWord, language) != null) {
                return findAllEntriesWithWord(possibleFuzzyWord, language, translationLanguage);
            }
            return null;
        }
    }

    private String findFuzzyMatchForWord(String word, String language, String translationLanguage) {
        List<MatchesEntry> foundEntriesWithLanguages = matchesRepository.findAllEntriesWithLanguageAndTranslationLanguage(language, translationLanguage);

        List<WordsEntry> possibleWords = new LinkedList<>();
        for (MatchesEntry m : foundEntriesWithLanguages) {
            if (m.getWord_language().equals(language)) {
                possibleWords.add(wordsService.findById(m.getWord_id()));
            }
            if (m.getMatching_word_language().equals(language)) {
                possibleWords.add(wordsService.findById(m.getMatching_word_id()));
            }
        }


        WordsEntry mostLikelyWord = null;
        int fuzzyMatchRate = FUZZY_MATCH_LIMITER;

        for (WordsEntry w : possibleWords) {
            int currentWordLevenshteinRate = Levenshtein.ld(word, w.getWord());
            if (currentWordLevenshteinRate <= fuzzyMatchRate) {
                mostLikelyWord = w;
                fuzzyMatchRate = currentWordLevenshteinRate;
            }
        }

        if (mostLikelyWord != null) {
            return mostLikelyWord.getWord();
        }
        return null;
    }

    public List<MatchesEntry> findAlreadyEnteredMatchWithWords(Long wordId, Long matchingWordId) {
        try {
            return matchesRepository.findAlreadyEnteredMatchWithWords(wordId, matchingWordId);
        } catch (Exception e) {
            return null;
        }
    }

    @CrossOrigin
    @PostMapping
    public MatchesEntry saveMatchesEntry(@RequestBody MatchesEntry matchesEntry) {
        return matchesRepository.save(matchesEntry);
    }
}
