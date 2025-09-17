package br.com.fiap.herosheets.habilidade;

import br.com.fiap.herosheets.sistema.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
    List<Habilidade> findBySistema(String sistema);
}
