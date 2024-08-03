package T.project.translator;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class TranslationService {
    private static final int MAX_THREADS = 10;
    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
    private final RestTemplate restTemplate = new RestTemplate();
    private final String yandexApiKey = "YOUR_YANDEX_API_KEY";  // Замените на ваш ключ API

    public String translateText(String text, String sourceLang, String targetLang) {
        String[] words = text.split(" ");
        Future<String>[] futures = new Future[words.length];

        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            futures[i] = executorService.submit(() -> translateWord(word, sourceLang, targetLang));
        }

        StringBuilder translatedText = new StringBuilder();
        for (Future<String> future : futures) {
            try {
                translatedText.append(future.get()).append(" ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return translatedText.toString().trim();
    }

    private String translateWord(String word, String sourceLang, String targetLang) {
        String url = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexApiKey +
                "&text=" + word + "&lang=" + sourceLang + "-" + targetLang;
        TranslationResponse response = restTemplate.getForObject(url, TranslationResponse.class);
        return response != null ? response.getText().get(0) : word;
    }
}