package br.com.fiap.herosheets.ficha;

import br.com.fiap.herosheets.campanha.Campanha;
import br.com.fiap.herosheets.campanha.CampanhaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FichaService {
    private final FichaRepository fichaRepository;

    public FichaService(FichaRepository fichaRepository) {
        this.fichaRepository = fichaRepository;
    }

    public List<Ficha> getAllFichas(){
        return  fichaRepository.findAll();
    }
}
