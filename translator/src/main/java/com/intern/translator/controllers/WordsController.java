package com.intern.translator.controllers;


import com.intern.translator.models.MatchesEntry;
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
    @GetMapping("allWords")
    public List<WordsEntry> getAllWords() {return wordsService.getAllWords();}

    @CrossOrigin
    @GetMapping("estonian")
    public List<WordsEntry> getAllEstonianWords() {
        return wordsService.findAllEstonianWords();
    }

    @CrossOrigin
    @GetMapping("estonian/{word}")
    public List<WordsEntry> getEstonianWord(@PathVariable(value = "word") String word) {
        return wordsService.findByWordInEstonian(word);
    }

    @CrossOrigin
    @GetMapping("{id}")
    public WordsEntry getWordsEntry(@PathVariable Long id) {
        return wordsService.findById(id);
    }

    @CrossOrigin
    @GetMapping("findWordId/{language}/{word}")
    public WordsEntry findWordId(@PathVariable(value="word") String word,
                           @PathVariable(value="language") String language) {
        return wordsService.findWordId(word, language);
    }

    @CrossOrigin
    @PostMapping
    public WordsEntry saveWordsEntry(@RequestBody WordsEntry wordsEntry) {return wordsService.save(wordsEntry);}

    @CrossOrigin
    public MatchesEntry saveWordsPair(@RequestBody WordsEntry wordsEntry1, @RequestBody WordsEntry wordsEntry2) {
        WordsEntry savedWord1 = saveWordsEntry(wordsEntry1);
        WordsEntry savedWord2 = saveWordsEntry(wordsEntry2);

        MatchesEntry newMatchesEntry = new MatchesEntry(savedWord1.getWord_id(),
                savedWord1.getLanguage(),
                savedWord2.getWord_id(),
                savedWord2.getLanguage());

        return matchesService.saveMatchesEntry(newMatchesEntry);
    }
}
