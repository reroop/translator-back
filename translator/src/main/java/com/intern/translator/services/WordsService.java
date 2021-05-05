package com.intern.translator.services;

import com.intern.translator.models.WordsEntry;
import com.intern.translator.repositories.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordsService {

    @Autowired
    private WordsRepository wordsRepository;

    public WordsEntry findWordId(String word, String language) {
        return wordsRepository.findWordId(word, language);
    }

    public List<WordsEntry> findAllEstonianWords() {
        return wordsRepository.findAllEstonianWords();
    }

    public List<WordsEntry> getAllWords() {
        return wordsRepository.getAllWords();
    }

    public List<WordsEntry> findByWordInEstonian(String word) {
        return wordsRepository.findByWordInEstonian(word);
    }

    public WordsEntry save(WordsEntry wordsEntry) {
        //todo: add not null checks for word and language
        return wordsRepository.save(wordsEntry);
    }

    public WordsEntry findById(Long id) {
        //todo: orElseThrow some exception
        return wordsRepository.findById(id).orElse(new WordsEntry("estonian", "ERROR"));
    }
}
