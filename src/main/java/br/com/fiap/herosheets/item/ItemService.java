package br.com.fiap.herosheets.item;

import br.com.fiap.herosheets.sistema.Sistema;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItensPorSistema(Sistema sistema) {
        return itemRepository.findBySistema(sistema);
    }

    public void popularItensNoSistema(Sistema sistema, List<Item> itens) {
        itens.forEach(i -> i.setSistema(sistema));
        itemRepository.saveAll(itens);
    }
}
