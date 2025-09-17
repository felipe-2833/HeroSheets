package br.com.fiap.herosheets.atributo;

import br.com.fiap.herosheets.sistema.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtributoRepository extends JpaRepository<Atributo, Long> {

    List<Atributo> findBySistema(String sistema);
}
