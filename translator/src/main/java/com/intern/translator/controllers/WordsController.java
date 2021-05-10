package com.intern.translator.controllers;


import com.intern.translator.models.MatchesEntry;
import com.intern.translator.models.UserTranslation;
import com.intern.translator.models.WordsEntry;
import com.intern.translator.services.MatchesService;
import com.intern.translator.services.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("words")
@RestController
public class WordsController {

    @Autowired
    private WordsService wordsService;

    @Autowired
    private MatchesService matchesService;

    @CrossOrigin
    @GetMapping("languageWords/{language}")
    public List<WordsEntry> getAllWordsWithLanguage(@PathVariable(value = "language") String language) {
        return wordsService.findAllLanguageWords(language);
    }

    @CrossOrigin
    @GetMapping("{id}")
    public WordsEntry getWordsEntry(@PathVariable Long id) {
        return wordsService.findById(id);
    }

    @CrossOrigin
    @GetMapping("findWordId/{language}/{word}")
    public WordsEntry findWordId(@PathVariable(value = "word") String word,
                                 @PathVariable(value = "language") String language) {
        return wordsService.findWordId(word, language);
    }

    @CrossOrigin
    @PostMapping
    public WordsEntry saveWordsEntry(WordsEntry wordsEntry) {
        return wordsService.save(wordsEntry);
    }


    @PostMapping(value = "saveNewTranslation")
    @CrossOrigin
    public MatchesEntry saveWordsPair(@RequestBody UserTranslation userTranslation) {

        WordsEntry savedWord1 = wordsService.findWordId(userTranslation.getWord(), userTranslation.getWordLanguage());
        WordsEntry savedWord2 = wordsService.findWordId(userTranslation.getMatchingWord(), userTranslation.getMatchingWordLanguage());

        if (savedWord1 != null & savedWord2 != null) {  //to avoid multiple same translations
            if (matchesService.findAlreadyEnteredMatchWithWords(savedWord1.getWord_id(), savedWord2.getWord_id()) != null) {
                return null;
            }
        }

        if (savedWord1 == null) {
            savedWord1 = saveWordsEntry(new WordsEntry(userTranslation.getWordLanguage(), userTranslation.getWord()));
        }

        if (savedWord2 == null) {
            savedWord2 = saveWordsEntry(new WordsEntry(userTranslation.getMatchingWordLanguage(), userTranslation.getMatchingWord()));
        }

        MatchesEntry newMatchesEntry = new MatchesEntry(savedWord1.getWord_id(),
                savedWord1.getLanguage(),
                savedWord2.getWord_id(),
                savedWord2.getLanguage()
        );

        return matchesService.saveMatchesEntry(newMatchesEntry);
    }

}
