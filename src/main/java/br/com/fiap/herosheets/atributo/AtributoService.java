package br.com.fiap.herosheets.atributo;

import br.com.fiap.herosheets.sistema.Sistema;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtributoService {

    private final AtributoRepository atributoRepository;

    public AtributoService(AtributoRepository atributoRepository) {
        this.atributoRepository = atributoRepository;
    }

    public List<Atributo> getAtributosPorSistema(Sistema sistema) {
        return atributoRepository.findBySistema(sistema);
    }

    public void popularAtributosNoSistema(Sistema sistema, List<Atributo> atributos) {
        atributos.forEach(a -> a.setSistema(sistema));
        atributoRepository.saveAll(atributos);
    }
}
