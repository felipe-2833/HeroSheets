package br.com.fiap.herosheets.ficha;

import br.com.fiap.herosheets.campanha.CampanhaService;
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
    public String index(Model model){
        var fichas = fichaService.getAllFichas();
        model.addAttribute("fichas", fichas);
        return "index";
    }

}
