package com.intern.translator;

import com.intern.translator.models.MatchesEntry;
import com.intern.translator.models.WordsEntry;
import com.intern.translator.repositories.MatchesRepository;
import com.intern.translator.repositories.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BackendApplicationInit implements CommandLineRunner {

    @Autowired
    private WordsRepository wordsRepository;

    @Autowired
    private MatchesRepository matchesRepository;

    @Override
    public void run(String... args) {
        /**
         * WARNING: DO NOT CHANGE, tests depend on these entries
         */


        WordsEntry autoEST = new WordsEntry("estonian", "auto");
        WordsEntry carENG = new WordsEntry("english", "car");
        WordsEntry carroSPA = new WordsEntry("spanish", "carro");
        WordsEntry vesiEST = new WordsEntry("estonian", "vesi");
        WordsEntry waterENG = new WordsEntry("english", "water");
        WordsEntry aquaENG = new WordsEntry("english", "aqua");
        WordsEntry aguaSPA = new WordsEntry("spanish", "agua");
        WordsEntry soiduautoEST = new WordsEntry("estonian", "sõiduauto");
        WordsEntry automobileENG = new WordsEntry("english", "automobile");

        List<WordsEntry> wordsEntries = List.of(autoEST, carENG, carroSPA, vesiEST, waterENG,
                aquaENG, aguaSPA, soiduautoEST, automobileENG);
        for (WordsEntry w : wordsEntries) {
            wordsRepository.save(w);
        }

        MatchesEntry autoESTENG = new MatchesEntry(1L, "estonian", 2L, "english");
        MatchesEntry autoESTSPA = new MatchesEntry(1L, "estonian", 3L, "spanish");
        MatchesEntry vesiESTENG = new MatchesEntry(4L, "estonian", 5L, "english");
        MatchesEntry vesiESTENG2 = new MatchesEntry(4L, "estonian", 6L, "english");
        MatchesEntry vesiESTSPA = new MatchesEntry(4L, "estonian", 7L, "spanish");
        MatchesEntry carENGEST = new MatchesEntry(2L, "english", 8L, "estonian");
        MatchesEntry automobileENGEST = new MatchesEntry(9L, "english", 8L, "estonian");
        MatchesEntry automobileENGEST2 = new MatchesEntry(9L, "english", 1L, "estonian");

        List<MatchesEntry> matchesEntries = List.of(autoESTENG, autoESTSPA, vesiESTENG, vesiESTENG2, vesiESTSPA,
                carENGEST, automobileENGEST, automobileENGEST2);

        for (MatchesEntry m : matchesEntries) {
            matchesRepository.save(m);
        }

    }
}
