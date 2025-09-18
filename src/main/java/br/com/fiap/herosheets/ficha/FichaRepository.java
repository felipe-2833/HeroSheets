package br.com.fiap.herosheets.ficha;

import br.com.fiap.herosheets.campanha.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FichaRepository extends JpaRepository<Ficha, Long> {

    List<Ficha> findByCampanha(Campanha campanha);
}
