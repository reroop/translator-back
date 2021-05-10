package com.intern.translator.controllers;

import com.intern.translator.models.WordsEntry;
import com.intern.translator.services.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("matches")
@RestController
public class MatchesController {

    @Autowired
    private MatchesService matchesService;

    @CrossOrigin
    @GetMapping("{language}/{word}/{translationLanguage}")
    public List<WordsEntry> getResultsForWordWithTranslationLanguage(@PathVariable(value="language") String language,
                                                                     @PathVariable(value="word") String word,
                                                                     @PathVariable(value="translationLanguage") String translationLanguage) {
        return matchesService.findAllEntriesWithWord(word, language, translationLanguage);
    }
}
