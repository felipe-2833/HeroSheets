package br.com.fiap.herosheets.campanha;

import br.com.fiap.herosheets.config.MessageHelper;
import br.com.fiap.herosheets.sistema.Sistema;
import br.com.fiap.herosheets.sistema.SistemaRepository;
import br.com.fiap.herosheets.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/campanha")
public class CampanhaController {

    private final CampanhaService campanhaService;
    private final SistemaRepository sistemaRepository;
    private final MessageSource messageSource;
    private final MessageHelper messageHelper;
    private final UserService userService;

    @GetMapping
    public String index(Model model,@AuthenticationPrincipal OAuth2User user){
        var campanhas = campanhaService.getAllCampanhas();
        model.addAttribute("campanhas", campanhas);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/formcampanha")
    public String form(Model model, @AuthenticationPrincipal OAuth2User user){
        List<Sistema> sistemas = sistemaRepository.findAll();
        model.addAttribute("sistemas", sistemas);
        model.addAttribute("campanha", new Campanha());
        model.addAttribute("userr", user);
        return "form-campanha";
    }

    @PostMapping("/formcampanha")
    public String create(Campanha campanha, BindingResult result, RedirectAttributes redirect, @AuthenticationPrincipal OAuth2User principal){
        if(result.hasErrors()) return "form-campanha";
        var user = userService.register(principal);
        campanha.setUser(user);
        campanhaService.save(campanha);
        redirect.addFlashAttribute("message", messageHelper.get("campanha.create.success"));
        return "redirect:/campanha"; //301
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect ){
        campanhaService.deleteById(id);
        redirect.addFlashAttribute("message", messageHelper.get("campanha.delete.success"));
        return "redirect:/campanha";
    }

    @PutMapping("/pick/{id}")
    public String pick(@PathVariable Long id, @AuthenticationPrincipal OAuth2User principal){
        campanhaService.pick(id, userService.register(principal));
        return "redirect:/campanha";
    }

    @PutMapping("/rename/{id}")
    public String rename(@PathVariable Long id, @RequestParam String novoNome, @AuthenticationPrincipal OAuth2User principal) {
        campanhaService.rename(id, novoNome, userService.register(principal));
        return "redirect:/campanha";
    }

    @PutMapping("/drop/{id}")
    public String drop(@PathVariable Long id, @AuthenticationPrincipal OAuth2User principal){
        campanhaService.drop(id, userService.register(principal));
        return "redirect:/campanha";
    }

    @PutMapping("/inc/{id}")
    public String increment(@PathVariable Long id, @AuthenticationPrincipal OAuth2User principal){
        campanhaService.incrementCampanhaQtdPlayers(id, userService.register(principal));
        return "redirect:/campanha";
    }

    @PutMapping("/dec/{id}")
    public String decrement(@PathVariable Long id, @AuthenticationPrincipal OAuth2User principal){
        campanhaService.decrementCampanhaQtdPlayer(id, userService.register(principal));
        return "redirect:/campanha";
    }
}
