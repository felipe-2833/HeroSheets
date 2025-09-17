package br.com.fiap.herosheets.sistema;

import br.com.fiap.herosheets.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SistemaRepository extends JpaRepository<Sistema, Long> {
}
