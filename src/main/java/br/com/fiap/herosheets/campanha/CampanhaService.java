package br.com.fiap.herosheets.campanha;

import br.com.fiap.herosheets.config.MessageHelper;
import br.com.fiap.herosheets.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampanhaService {


    private final CampanhaRepository campanhaRepository;
    private final MessageHelper messageHelper;

    public CampanhaService(CampanhaRepository campanhaRepository, MessageHelper messageHelper) {
        this.campanhaRepository = campanhaRepository;
        this.messageHelper = messageHelper;
    }

    public List<Campanha> getAllCampanhas(){
        return  campanhaRepository.findAll();
    }

    public Campanha save(Campanha campanha) {
        return campanhaRepository.save(campanha);
    }

    public void deleteById(Long id) {
        campanhaRepository.delete(getCampanha(id));
    }

    public void pick(Long id) {
        var campanha = getCampanha(id);
        campanhaRepository.save(campanha);
    }

    public void drop(Long id) {
        var campanha = getCampanha(id);
        campanha.setUser(null);
        campanhaRepository.save(campanha);
    }

    public void rename(Long id, String novoNome) {
        var campanha = getCampanha(id);
        campanha.setName(novoNome);
        campanhaRepository.save(campanha);
    }

    public void incrementCampanhaQtdPlayers(Long id) {
        var campanha = getCampanha(id);
        campanha.setQtdPlayers(campanha.getQtdPlayers() + 1);
        if(campanha.getQtdPlayers() > 10) campanha.setQtdPlayers(10);
        campanhaRepository.save(campanha);
    }

    public void decrementCampanhaQtdPlayer(Long id) {
        var campanha = getCampanha(id);
        campanha.setQtdPlayers(campanha.getQtdPlayers() - 1);
        if(campanha.getQtdPlayers() < 0) campanha.setQtdPlayers(0);
        campanhaRepository.save(campanha);
    }

    private Campanha getCampanha(Long id) {
        return campanhaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(messageHelper.get("campanha.notfound"))
        );
    }

}
