package br.com.fiap.herosheets.ficha;

import br.com.fiap.herosheets.atributo.Atributo;
import br.com.fiap.herosheets.campanha.Campanha;
import br.com.fiap.herosheets.campanha.CampanhaRepository;
import br.com.fiap.herosheets.campanha.CampanhaService;
import br.com.fiap.herosheets.config.MessageHelper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaService {
    private final FichaRepository fichaRepository;
    private final CampanhaService campanhaService;
    private final MessageHelper messageHelper;

    public FichaService(FichaRepository fichaRepository, CampanhaService campanhaService, MessageHelper messageHelper) {
        this.fichaRepository = fichaRepository;
        this.campanhaService = campanhaService;
        this.messageHelper = messageHelper;
    }

    public List<Ficha> getAllFichas(){
        return  fichaRepository.findAll();
    }

    public Ficha save(Ficha ficha) {
        return fichaRepository.save(ficha);
    }

    public List<Ficha> getFichasByCampanha(Long campanhaId) {
        Campanha campanha = campanhaService.getCampanha(campanhaId);
        return fichaRepository.findByCampanha(campanha);
    }

    public void rename(Long id, String novoNome) {
        var ficha = getFicha(id);
        ficha.setNome(novoNome);
        fichaRepository.save(ficha);
    }

    public void incrementAtributo(Long fichaId, Long atributoId) {
        Ficha ficha = getFicha(fichaId);
        Atributo atributo = ficha.getAtributos().stream()
                .filter(a -> a.getId().equals(atributoId))
                .findFirst()
                .orElseThrow();
        atributo.setValorAtual(Math.min(atributo.getValorAtual() + 1, atributo.getValorMaximo()));
        fichaRepository.save(ficha);
    }

    public void decrementAtributo(Long fichaId, Long atributoId) {
        Ficha ficha = getFicha(fichaId);
        Atributo atributo = ficha.getAtributos().stream()
                .filter(a -> a.getId().equals(atributoId))
                .findFirst()
                .orElseThrow();
        atributo.setValorAtual(Math.max(atributo.getValorAtual() - 1, atributo.getValorMinimo()));
        fichaRepository.save(ficha);
    }

    // Nível
    public void incrementNivel(Long fichaId) {
        Ficha ficha = getFicha(fichaId);
        ficha.setNivel(Math.min(ficha.getNivel() + 1, 20)); // Exemplo: limite máximo 20
        fichaRepository.save(ficha);
    }

    public void decrementNivel(Long fichaId) {
        Ficha ficha = getFicha(fichaId);
        ficha.setNivel(Math.max(ficha.getNivel() - 1, 1)); // Exemplo: limite mínimo 1
        fichaRepository.save(ficha);
    }

    // Vida
    public void incrementVida(Long fichaId) {
        Ficha ficha = getFicha(fichaId);
        int max = ficha.getCampanha().getSistema().getVidaMax();
        ficha.setVida(Math.min(ficha.getVida() + 1, max));
        fichaRepository.save(ficha);
    }

    public void decrementVida(Long fichaId) {
        Ficha ficha = getFicha(fichaId);
        int min = ficha.getCampanha().getSistema().getVidaMin();
        ficha.setVida(Math.max(ficha.getVida() - 1, min));
        fichaRepository.save(ficha);
    }

    // Mana
    public void incrementMana(Long fichaId) {
        Ficha ficha = getFicha(fichaId);
        int max = ficha.getCampanha().getSistema().getManaMax();
        ficha.setMana(Math.min(ficha.getMana() + 1, max));
        fichaRepository.save(ficha);
    }

    public void decrementMana(Long fichaId) {
        Ficha ficha = getFicha(fichaId);
        int min = ficha.getCampanha().getSistema().getManaMin();
        ficha.setMana(Math.max(ficha.getMana() - 1, min));
        fichaRepository.save(ficha);
    }


    public Ficha getFicha(Long id) {
        return fichaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(messageHelper.get("ficha.notfound"))
        );
    }
}
