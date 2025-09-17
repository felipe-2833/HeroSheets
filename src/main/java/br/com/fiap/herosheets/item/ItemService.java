package br.com.fiap.herosheets.item;

import br.com.fiap.herosheets.atributo.Atributo;
import br.com.fiap.herosheets.sistema.Sistema;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> buscarPorSistema(String sistema) {
        return itemRepository.findBySistema(sistema);
    }
}
