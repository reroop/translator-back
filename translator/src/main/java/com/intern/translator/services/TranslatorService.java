package com.intern.translator.services;

import com.intern.translator.dto.TranslationResultDto;
import com.intern.translator.models.TranslationMatches;
import com.intern.translator.repositories.TranslatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TranslatorService {

    @Autowired
    private TranslatorRepository translatorRepository;

    /*
    public List<String> findWordExactMatchesLikeIgnoreCase(String searchableWord) {
        List<String> wordAllMatches = translatorRepository.findWordExactMatchesLikeIgnoreCase("%"+searchableWord.toLowerCase()+"%");
        System.out.println(wordAllMatches);
        return wordAllMatches;
    }

     */


    private TranslationResultDto convertTranslationResult(List<String> matches) {
        TranslationResultDto resultDto = new TranslationResultDto();
        resultDto.setMatches(matches);
        return resultDto;
    }
}
