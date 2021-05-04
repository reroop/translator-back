package com.intern.translator;

import com.intern.translator.models.TranslationEntry;
import com.intern.translator.models.TranslationMatches;
import com.intern.translator.models.WordsEntry;
import com.intern.translator.repositories.TranslatorRepository;
import com.intern.translator.repositories.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BackendApplicationInit implements CommandLineRunner {

    @Autowired
    private WordsRepository wordsRepository;

    @Override
    public void run(String... args) throws Exception {
        WordsEntry autoEST = new WordsEntry("estonian", "auto");
        WordsEntry carENG = new WordsEntry("english", "car");
        WordsEntry carroSPA = new WordsEntry("spanish", "carro");
        WordsEntry vesiEST = new WordsEntry("estonian", "vesi");
        WordsEntry waterENG = new WordsEntry("english", "water");
        WordsEntry aquaENG = new WordsEntry("english", "aqua");
        WordsEntry aguaSPA = new WordsEntry("spanish", "agua");

        List<WordsEntry> wordsEntries = List.of(autoEST,carENG,carroSPA, vesiEST, waterENG, aquaENG, aguaSPA);
        for (WordsEntry w: wordsEntries) {
            wordsRepository.save(w);
        }

    }
}
