package br.com.fiap.herosheets.habilidade;

import br.com.fiap.herosheets.atributo.Atributo;
import br.com.fiap.herosheets.sistema.Sistema;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadeService {

    private final HabilidadeRepository habilidadeRepository;

    public HabilidadeService(HabilidadeRepository habilidadeRepository) {
        this.habilidadeRepository = habilidadeRepository;
    }

    public List<Habilidade> buscarPorSistema(String sistema) {
        return habilidadeRepository.findBySistema(sistema);
    }

}
