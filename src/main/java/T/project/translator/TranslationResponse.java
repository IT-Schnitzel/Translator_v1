package T.project.translator;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TranslationResponse {
    @JsonProperty("code")
    private int code;

    @JsonProperty("lang")
    private String lang;

    @JsonProperty("text")
    private List<String> text;

    // Getters and Setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }
}