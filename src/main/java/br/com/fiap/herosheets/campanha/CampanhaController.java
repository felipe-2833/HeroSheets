package br.com.fiap.herosheets.campanha;

import br.com.fiap.herosheets.sistema.Sistema;
import br.com.fiap.herosheets.sistema.SistemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/campanha")
public class CampanhaController {

    private final CampanhaService campanhaService;
    private final SistemaRepository sistemaRepository;
    private final MessageSource messageSource;

    @GetMapping
    public String index(Model model){
        var campanhas = campanhaService.getAllCampanhas();
        model.addAttribute("campanhas", campanhas);
        return "index";
    }

    @GetMapping("/formcampanha")
    public String form(Model model){
        List<Sistema> sistemas = sistemaRepository.findAll();
        model.addAttribute("sistemas", sistemas);
        model.addAttribute("campanha", new Campanha());
        return "form-campanha";
    }

    @PostMapping("/formcampanha")
    public String create(Campanha campanha, BindingResult result, RedirectAttributes redirect  ){
        if(result.hasErrors()) return "form";
        var message = messageSource.getMessage("campanha.create.success", null, LocaleContextHolder.getLocale());
        campanhaService.save(campanha);
        redirect.addFlashAttribute("message", message);
        return "redirect:/campanha"; //301
    }
}
