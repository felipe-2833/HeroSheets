package br.com.fiap.herosheets.campanha;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/formcampanha")
    public String form(){
        return "formcampanha";
    }

    @PostMapping("/formcampanha")
    public String create(Campanha campanha, RedirectAttributes redirect ){ //session
        CampanhaService.save(campanha);
        redirect.addFlashAttribute("message", "Campanha criada com sucesso!");
        return "redirect:/campanha"; //301
    }
}
