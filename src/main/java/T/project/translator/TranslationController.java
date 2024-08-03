package T.project.translator;

import T.project.translator.model.TranslationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/translate")
public class TranslationController {
    @Autowired
    private TranslationService translationService;
    @Autowired
    private TranslationRequestRepository translationRequestRepository;

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("translationRequest", new TranslationRequest());
        return "translate";
    }

    @PostMapping
    public String translate(@ModelAttribute TranslationRequest translationRequest, Model model, HttpServletRequest request) {
        String translatedText = translationService.translateText(
                translationRequest.getText(),
                translationRequest.getSourceLang(),
                translationRequest.getTargetLang());

        String ipAddress = request.getRemoteAddr();
        translationRequestRepository.saveTranslationRequest(ipAddress, translationRequest.getText(), translatedText);

        model.addAttribute("translatedText", translatedText);
        return "translate";
    }
}