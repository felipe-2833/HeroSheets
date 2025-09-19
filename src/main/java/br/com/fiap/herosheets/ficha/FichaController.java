package br.com.fiap.herosheets.ficha;

import br.com.fiap.herosheets.campanha.Campanha;
import br.com.fiap.herosheets.campanha.CampanhaService;
import br.com.fiap.herosheets.config.MessageHelper;
import br.com.fiap.herosheets.sistema.Sistema;
import br.com.fiap.herosheets.sistema.SistemaRepository;
import br.com.fiap.herosheets.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ficha")
public class FichaController {

    private final FichaService fichaService;
    private final CampanhaService campanhaService;
    private final SistemaRepository sistemaRepository;
    private final MessageSource messageSource;
    private final MessageHelper messageHelper;
    private final UserService userService;

    @GetMapping
    public String index(@RequestParam(required = false) Long campanhaId, Model model){
        if (campanhaId != null) {
            Campanha campanha = campanhaService.getCampanha(campanhaId);
            List<Ficha> fichas = fichaService.getFichasByCampanha(campanhaId);
            model.addAttribute("fichas", fichas);
            model.addAttribute("campanhaSelecionada", campanha);
        } else {
            model.addAttribute("fichas", fichaService.getAllFichas());
        }

        return "ficha";
    }

    @GetMapping("/formficha")
    public String form(@RequestParam Long campanhaId, Model model){

        Campanha campanha = campanhaService.getCampanha(campanhaId);
        model.addAttribute("campanha", campanha);

        // Carrega o sistema da campanha
        Sistema sistema = campanha.getSistema();
        model.addAttribute("sistema", sistema);

        Ficha ficha = new Ficha();
        ficha.setNivel(1);
        model.addAttribute("ficha", ficha);

        return "form-ficha";
    }

    @PostMapping("/formficha")
    public String create(Ficha ficha, BindingResult result, RedirectAttributes redirect  ){
        if(result.hasErrors()) return "form-ficha";
        fichaService.save(ficha);
        redirect.addFlashAttribute("message", messageHelper.get("ficha.create.success"));
        return "redirect:/ficha"; //301
    }

}
