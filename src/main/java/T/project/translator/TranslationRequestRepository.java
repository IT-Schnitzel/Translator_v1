package T.project.translator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class TranslationRequestRepository {
    private final JdbcTemplate jdbcTemplate;

    public TranslationRequestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveTranslationRequest(String ipAddress, String inputText, String translatedText) {
        String sql = "INSERT INTO translation_requests (ip_address, input_text, translated_text) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, ipAddress, inputText, translatedText);
    }
}
