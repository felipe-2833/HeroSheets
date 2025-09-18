package br.com.fiap.herosheets.ficha;

import br.com.fiap.herosheets.campanha.Campanha;
import br.com.fiap.herosheets.campanha.CampanhaRepository;
import br.com.fiap.herosheets.campanha.CampanhaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaService {
    private final FichaRepository fichaRepository;
    private final CampanhaService campanhaService;

    public FichaService(FichaRepository fichaRepository, CampanhaService campanhaService) {
        this.fichaRepository = fichaRepository;
        this.campanhaService = campanhaService;
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
}
