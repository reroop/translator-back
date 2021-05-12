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
        WordsEntry checkEntry = new WordsEntry(language.toLowerCase(), word.toLowerCase());
        if (wordsRepository.exists(Example.of(checkEntry))) {
            return wordsRepository.findWordId(word, language);
        }
        else {
            return null;
        }
    }

    public WordsEntry save(WordsEntry wordsEntry) {
        if (wordsEntry.getWord() == null || wordsEntry.getLanguage() == null) {
            return null;
        }
        return wordsRepository.save(wordsEntry);
    }

    public WordsEntry findById(Long id) {
        // orElse works, but not very elegant
        return wordsRepository.findById(id).orElse(new WordsEntry("estonian", "ERROR"));
    }

    public List<WordsEntry> findAllLanguageWords(String language) {
        return wordsRepository.findAllLanguageWords(language);
    }

    public List<String> getAllSavedLanguages() {
        return wordsRepository.getAllSavedLanguages();
    }
}
