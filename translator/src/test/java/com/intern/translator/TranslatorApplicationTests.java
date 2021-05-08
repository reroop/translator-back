package com.intern.translator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.translator.models.MatchesEntry;
import com.intern.translator.models.UserTranslation;
import com.intern.translator.models.WordsEntry;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TranslatorApplicationTests {
	/**
	 * WARNING: these are end-to-end tests, therefore in order to successfully run them, you first need to freshly
	 * start TranslatorApplication and THEN run tests. Running tests multiple times in one TranslatorApplication session
	 * will yield false negative results.
	 */



	ObjectMapper mapper = new ObjectMapper();
	private static final String getAllEstonianWordsRes =
			"[\n" +
			"    {\n" +
			"        \"word_id\": 1,\n" +
			"        \"language\": \"estonian\",\n" +
			"        \"word\": \"auto\"\n" +
			"    },\n" +
			"    {\n" +
			"        \"word_id\": 4,\n" +
			"        \"language\": \"estonian\",\n" +
			"        \"word\": \"vesi\"\n" +
			"    },\n" +
			"    {\n" +
			"        \"word_id\": 8,\n" +
			"        \"language\": \"estonian\",\n" +
			"        \"word\": \"sõiduauto\"\n" +
			"    }\n" +
			"]";

	private static final String carEnglishToEstonianResult =
			"[\n" +
			"    {\n" +
			"        \"word_id\": 8,\n" +
			"        \"language\": \"estonian\",\n" +
			"        \"word\": \"sõiduauto\"\n" +
			"    },\n" +
			"    {\n" +
			"        \"word_id\": 1,\n" +
			"        \"language\": \"estonian\",\n" +
			"        \"word\": \"auto\"\n" +
			"    }\n" +
			"]";

	private static final String saveExpectedResult = "[\n" +
			"    {\n" +
			"        \"word_id\": 18,\n" +
			"        \"language\": \"estonian\",\n" +
			"        \"word\": \"kuu\"\n" +
			"    }\n" +
			"]";

	@Test
	@Order(2)
	void apiIsRunning() {
		assertThat(this.restTemplate.getForObject("http://localhost:8080/api", String.class)).contains("Backend API is up");
	}

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(3)
	void getAllEstonianWords() throws JsonProcessingException {
		String result = this.restTemplate.getForObject("http://localhost:8080/api/words/languageWords/estonian", String.class);
		assertEquals(mapper.readTree(getAllEstonianWordsRes), mapper.readTree(result));
	}

	@Test
	@Order(4)
	void getWordById() {
		WordsEntry expectedEntry = new WordsEntry("estonian", "auto");
		WordsEntry response = this.restTemplate.getForObject("http://localhost:8080/api/words/1", WordsEntry.class);
		assertEquals(expectedEntry.getLanguage(), response.getLanguage());
		assertEquals(expectedEntry.getWord(), response.getWord());
	}

	@Test
	@Order(5)
	void findWordId() {
		WordsEntry response = this.restTemplate.getForObject("http://localhost:8080/api/words/findWordId/estonian/auto", WordsEntry.class);
		assertEquals(1L, response.getWord_id());
	}

	@Test
	@Order(6)
	void getMatchForWord() throws JsonProcessingException {
		String result = this.restTemplate.getForObject("http://localhost:8080/api/matches/english/car/estonian", String.class);
		assertEquals(mapper.readTree(carEnglishToEstonianResult), mapper.readTree(result));
	}


	@Test
	@Order(7)
	void savingNewTranslationIsSuccessful() throws JsonProcessingException {
		UserTranslation testTranslation = new UserTranslation("estonian", "kuu", "english", "month");
		MatchesEntry saveTranslation = this.restTemplate.postForObject("http://localhost:8080/api/words/saveNewTranslation", testTranslation, MatchesEntry.class);
		assertNotNull(saveTranslation);
		String res = this.restTemplate.getForObject("http://localhost:8080/api/matches/english/month/estonian", String.class);
		assertEquals(mapper.readTree(saveExpectedResult), mapper.readTree(res));
	}

	@Test
	@Order(8)
	void fuzzyMatchWorks() throws JsonProcessingException {
		String result = this.restTemplate.getForObject("http://localhost:8080/api/matches/english/carAS/estonian", String.class);
		assertEquals(mapper.readTree(carEnglishToEstonianResult), mapper.readTree(result));
	}
}
