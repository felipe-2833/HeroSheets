package br.com.fiap.herosheets.ficha;

import br.com.fiap.herosheets.campanha.CampanhaService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ficha")
public class FichaController {

    private final FichaService fichaService;

    public FichaController(FichaService fichaService) {
        this.fichaService = fichaService;
    }

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        var fichas = fichaService.getAllFichas();
        model.addAttribute("fichas", fichas);
        return "index";
    }

}
