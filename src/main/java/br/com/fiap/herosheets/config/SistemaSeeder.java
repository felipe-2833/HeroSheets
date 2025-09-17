package br.com.fiap.herosheets.config;

import br.com.fiap.herosheets.atributo.Atributo;
import br.com.fiap.herosheets.atributo.AtributoRepository;
import br.com.fiap.herosheets.atributo.AtributoService;
import br.com.fiap.herosheets.habilidade.Habilidade;
import br.com.fiap.herosheets.habilidade.HabilidadeRepository;
import br.com.fiap.herosheets.habilidade.HabilidadeService;
import br.com.fiap.herosheets.item.Item;
import br.com.fiap.herosheets.item.ItemRepository;
import br.com.fiap.herosheets.item.ItemService;
import br.com.fiap.herosheets.sistema.Sistema;
import br.com.fiap.herosheets.sistema.SistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SistemaSeeder {

    @Autowired
    private SistemaRepository sistemaRepository;

    @Autowired
    private AtributoRepository atributoRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private HabilidadeRepository habilidadeRepository;

}
