package com.intern.translator;

import com.intern.translator.models.TranslationEntry;
import com.intern.translator.models.TranslationMatches;
import com.intern.translator.repositories.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class BackendApplicationInit implements CommandLineRunner {

    @Autowired
    private TranslatorRepository translatorRepository;

    @Override
    public void run(String... args) throws Exception {
        TranslationEntry autoEST = new TranslationEntry("auto", "EST");
        TranslationMatches autoEST_ENGMatches = new TranslationMatches("ENG", Arrays.asList("car", "automobile"));
        TranslationMatches autoEST_SPAMatches = new TranslationMatches("SPA", Collections.singletonList("carro"));

        TranslationEntry vesiEST = new TranslationEntry("vesi", "EST");
        TranslationMatches vesiEST_ENGMatches = new TranslationMatches("ENG", Arrays.asList("water", "aqua"));
        TranslationMatches vesiEST_SPAMatches = new TranslationMatches("SPA", Collections.singletonList("agua"));
    }
}
