package br.com.fiap.herosheets.ficha;

import br.com.fiap.herosheets.atributo.Atributo;
import br.com.fiap.herosheets.atributo.AtributoRepository;
import br.com.fiap.herosheets.campanha.Campanha;
import br.com.fiap.herosheets.campanha.CampanhaService;
import br.com.fiap.herosheets.config.MessageHelper;
import br.com.fiap.herosheets.habilidade.Habilidade;
import br.com.fiap.herosheets.habilidade.HabilidadeRepository;
import br.com.fiap.herosheets.item.Item;
import br.com.fiap.herosheets.item.ItemRepository;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ficha")
public class FichaController {

    private final FichaService fichaService;
    private final ItemRepository itemRepository;
    private final HabilidadeRepository habilidadeRepository;
    private final AtributoRepository atributoRepository;
    private final CampanhaService campanhaService;
    private final SistemaRepository sistemaRepository;
    private final MessageSource messageSource;
    private final MessageHelper messageHelper;
    private final UserService userService;
    private final FichaRepository fichaRepository;

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
        List<Item> itens = itemRepository.findBySistema(sistema.getNome());
        sistema.setItens(itens);
        List<Habilidade> habilidades = habilidadeRepository.findBySistema(sistema.getNome());
        sistema.setHabilidades(habilidades);
        List<Atributo> atributos = atributoRepository.findBySistema(sistema.getNome());
        sistema.setAtributos(atributos);
        model.addAttribute("sistema", sistema);

        Ficha ficha = new Ficha();
        ficha.setNivel(1);

        List<Atributo> fichaAtributos = atributos.stream().map(a ->
                Atributo.builder()
                        .nome(a.getNome())
                        .valorMinimo(a.getValorMinimo())
                        .valorMaximo(a.getValorMaximo())
                        .valorAtual(a.getValorMaximo()) // default
                        .sistema(a.getSistema())
                        .build()
        ).collect(Collectors.toList());

        ficha.setAtributos(fichaAtributos);
        model.addAttribute("ficha", ficha);

        return "form-ficha";
    }

    @PostMapping("/formficha")
    public String create(Ficha ficha, BindingResult result, RedirectAttributes redirect, @RequestParam Long campanhaId  ){
        if(result.hasErrors()) return "form-ficha";

        Campanha campanha = campanhaService.getCampanha(campanhaId);
        ficha.setCampanha(campanha);

        if (ficha.getAtributos() != null) {
            List<Atributo> atributosClone = ficha.getAtributos().stream()
                    .map(a -> {
                        Atributo novo = new Atributo();
                        novo.setNome(a.getNome());
                        novo.setValorMinimo(a.getValorMinimo());
                        novo.setValorMaximo(a.getValorMaximo());
                        novo.setValorAtual(a.getValorAtual());
                        novo.setFicha(ficha); // muito importante!
                        return novo;
                    })
                    .toList();
            ficha.setAtributos(atributosClone);
        }

        if (ficha.getInventarioIds() != null && !ficha.getInventarioIds().isEmpty()) {
            List<Item> itens = ficha.getInventarioIds().stream()
                    .map(id -> itemRepository.getById(id))
                    .toList();
            ficha.setInventario(itens);
        }

        if (ficha.getHabilidadeIds() != null && !ficha.getHabilidadeIds().isEmpty()) {
            List<Habilidade> habilidades = ficha.getHabilidadeIds().stream()
                    .map(id -> habilidadeRepository.getById(id))
                    .toList();
            ficha.setHabilidades(habilidades);
        }

        if (ficha.getAtributos() != null) {
            ficha.getAtributos().forEach(a -> {
                if (a.getValorAtual() < a.getValorMinimo()) a.setValorAtual(a.getValorMinimo());
                if (a.getValorAtual() > a.getValorMaximo()) a.setValorAtual(a.getValorMaximo());
            });
        }

        fichaService.save(ficha);
        redirect.addAttribute("campanhaId", campanhaId);
        redirect.addFlashAttribute("message", messageHelper.get("ficha.create.success"));
        return "redirect:/ficha";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect, @RequestParam Long campanhaId) {
        fichaRepository.deleteById(id);
        redirect.addAttribute("campanhaId", campanhaId);
        redirect.addFlashAttribute("message", messageHelper.get("ficha.delete.success"));
        return "redirect:/ficha";
    }

    @PutMapping("/rename/{id}")
    public String rename(@PathVariable Long id, @RequestParam String novoNome, RedirectAttributes redirect, @RequestParam Long campanhaId) {
        fichaService.rename(id, novoNome);
        redirect.addAttribute("campanhaId", campanhaId);
        redirect.addFlashAttribute("message", messageHelper.get("ficha.rename.success"));
        return "redirect:/ficha";
    }

    @PutMapping("/inc/atributo/{fichaId}/{atributoId}")
    public String incrementAtributo(@PathVariable Long fichaId, @PathVariable Long atributoId, @RequestParam Long campanhaId) {
        fichaService.incrementAtributo(fichaId, atributoId);
        return "redirect:/ficha?campanhaId=" + campanhaId;
    }

    @PutMapping("/dec/atributo/{fichaId}/{atributoId}")
    public String decrementAtributo(@PathVariable Long fichaId, @PathVariable Long atributoId, @RequestParam Long campanhaId) {
        fichaService.decrementAtributo(fichaId, atributoId);
        return "redirect:/ficha?campanhaId=" + campanhaId;
    }

    @PutMapping("/inc/nivel/{fichaId}")
    public String incrementNivel(@PathVariable Long fichaId, @RequestParam Long campanhaId) {
        fichaService.incrementNivel(fichaId);
        return "redirect:/ficha?campanhaId=" + campanhaId;
    }

    @PutMapping("/dec/nivel/{fichaId}")
    public String decrementNivel(@PathVariable Long fichaId, @RequestParam Long campanhaId) {
        fichaService.decrementNivel(fichaId);
        return "redirect:/ficha?campanhaId=" + campanhaId;
    }

    // Vida
    @PutMapping("/inc/vida/{fichaId}")
    public String incrementVida(@PathVariable Long fichaId, @RequestParam Long campanhaId) {
        fichaService.incrementVida(fichaId);
        return "redirect:/ficha?campanhaId=" + campanhaId;
    }

    @PutMapping("/dec/vida/{fichaId}")
    public String decrementVida(@PathVariable Long fichaId, @RequestParam Long campanhaId) {
        fichaService.decrementVida(fichaId);
        return "redirect:/ficha?campanhaId=" + campanhaId;
    }

    // Mana
    @PutMapping("/inc/mana/{fichaId}")
    public String incrementMana(@PathVariable Long fichaId, @RequestParam Long campanhaId) {
        fichaService.incrementMana(fichaId);
        return "redirect:/ficha?campanhaId=" + campanhaId;
    }

    @PutMapping("/dec/mana/{fichaId}")
    public String decrementMana(@PathVariable Long fichaId, @RequestParam Long campanhaId) {
        fichaService.decrementMana(fichaId);
        return "redirect:/ficha?campanhaId=" + campanhaId;
    }



}
