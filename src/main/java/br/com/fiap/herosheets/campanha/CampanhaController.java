package br.com.fiap.herosheets.campanha;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campanha")
public class CampanhaController {

    private final CampanhaService campanhaService;

    public CampanhaController(CampanhaService campanhaService) {
        this.campanhaService = campanhaService;
    }

    @GetMapping
    public String index(Model model){
        var campanhas = campanhaService.getAllCampanhas();
        model.addAttribute("campanhas", campanhas);
        return "index";
    }
}
