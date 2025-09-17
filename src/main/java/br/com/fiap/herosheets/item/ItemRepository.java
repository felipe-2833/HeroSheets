package br.com.fiap.herosheets.item;

import br.com.fiap.herosheets.sistema.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findBySistema(String sistema);
}
