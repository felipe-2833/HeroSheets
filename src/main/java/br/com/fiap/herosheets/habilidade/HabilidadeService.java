package br.com.fiap.herosheets.habilidade;

import br.com.fiap.herosheets.sistema.Sistema;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadeService {

    private final HabilidadeRepository habilidadeRepository;

    public HabilidadeService(HabilidadeRepository habilidadeRepository) {
        this.habilidadeRepository = habilidadeRepository;
    }

    public List<Habilidade> getHabilidadesPorSistema(Sistema sistema) {
        return habilidadeRepository.findBySistema(sistema);
    }

    public void popularHabilidadesNoSistema(Sistema sistema, List<Habilidade> habilidades) {
        habilidades.forEach(h -> h.setSistema(sistema));
        habilidadeRepository.saveAll(habilidades);
    }

}
