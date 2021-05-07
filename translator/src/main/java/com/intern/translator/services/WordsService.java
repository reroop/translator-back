package com.intern.translator.services;

import com.intern.translator.models.WordsEntry;
import com.intern.translator.repositories.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordsService {

    @Autowired
    private WordsRepository wordsRepository;

    public WordsEntry findWordId(String word, String language) {
        WordsEntry checkEntry = new WordsEntry(language, word);
        if (wordsRepository.exists(Example.of(checkEntry))) {
            return wordsRepository.findWordId(word, language);
        }
        else {
            //System.out.println("no such word found: " + word + ", "+ language);
            return null;
        }
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
        if (wordsEntry.getWord() == null || wordsEntry.getLanguage() == null) {
            return null;
        }
        return wordsRepository.save(wordsEntry);
    }

    public WordsEntry findById(Long id) {
        //todo: orElseThrow some exception
        return wordsRepository.findById(id).orElse(new WordsEntry("estonian", "ERROR"));
    }
}
