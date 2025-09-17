package br.com.fiap.herosheets.campanha;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampanhaService {

    private final CampanhaRepository campanhaRepository;

    public CampanhaService(CampanhaRepository campanhaRepository) {
        this.campanhaRepository = campanhaRepository;
    }

    public List<Campanha> getAllCampanhas(){
        return  campanhaRepository.findAll();
    }

}
