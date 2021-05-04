package com.intern.translator.controllers;


import com.intern.translator.models.WordsEntry;
import com.intern.translator.services.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("words")
@RestController
public class WordsController {

    @Autowired
    private WordsService wordsService;

    @CrossOrigin
    @GetMapping("/estonian")
    public List<WordsEntry> getAllEstonianWords() {
        return wordsService.findAllEstonianWords();
    }

    @CrossOrigin
    @GetMapping("/estonian/{word}")
    public List<WordsEntry> getEstonianWord(@RequestParam(value = "word") String word) {
        return wordsService.findByWordInEstonian(word);
    }

    @CrossOrigin
    @GetMapping("{id}")
    public WordsEntry getWordsEntry(@PathVariable Long id) {
        return wordsService.findById(id);
    }


    @CrossOrigin
    @PostMapping
    public WordsEntry saveWordsEntry(@RequestBody WordsEntry wordsEntry) {return wordsService.save(wordsEntry);}
}
