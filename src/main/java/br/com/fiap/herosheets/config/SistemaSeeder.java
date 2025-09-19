package br.com.fiap.herosheets.config;

import br.com.fiap.herosheets.atributo.Atributo;
import br.com.fiap.herosheets.atributo.AtributoRepository;
import br.com.fiap.herosheets.habilidade.Habilidade;
import br.com.fiap.herosheets.habilidade.HabilidadeRepository;
import br.com.fiap.herosheets.item.Item;
import br.com.fiap.herosheets.item.ItemRepository;
import br.com.fiap.herosheets.sistema.Sistema;
import br.com.fiap.herosheets.sistema.SistemaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @PostConstruct
    public void init() {
        if (sistemaRepository.count() > 0) {
            return;
        }

        var atributos = List.of(
                Atributo.builder().nome("Força").valorAtual(10).valorMinimo(1).valorMaximo(20).sistema("dnd").ficha(null).build(),
                Atributo.builder().nome("Destreza").valorAtual(10).valorMinimo(1).valorMaximo(20).sistema("dnd").ficha(null).build(),
                Atributo.builder().nome("Constituição").valorAtual(10).valorMinimo(1).valorMaximo(20).sistema("dnd").ficha(null).build(),
                Atributo.builder().nome("Inteligência").valorAtual(10).valorMinimo(1).valorMaximo(20).sistema("dnd").ficha(null).build(),
                Atributo.builder().nome("Carisma").valorAtual(10).valorMinimo(1).valorMaximo(20).sistema("dnd").ficha(null).build(),
                Atributo.builder().nome("Força").valorAtual(10).valorMinimo(1).valorMaximo(20).sistema("tormenta").ficha(null).build(),
                Atributo.builder().nome("Destreza").valorAtual(10).valorMinimo(1).valorMaximo(20).sistema("tormenta").ficha(null).build(),
                Atributo.builder().nome("Inteligência").valorAtual(10).valorMinimo(1).valorMaximo(20).sistema("tormenta").ficha(null).build(),
                Atributo.builder().nome("Vigor").valorAtual(10).valorMinimo(1).valorMaximo(20).sistema("tormenta").ficha(null).build(),
                Atributo.builder().nome("Carisma").valorAtual(10).valorMinimo(1).valorMaximo(20).sistema("tormenta").ficha(null).build(),
                Atributo.builder().nome("Força").valorAtual(5).valorMinimo(1).valorMaximo(10).sistema("cthulhu").ficha(null).build(),
                Atributo.builder().nome("Destreza").valorAtual(5).valorMinimo(1).valorMaximo(10).sistema("cthulhu").ficha(null).build(),
                Atributo.builder().nome("Inteligência").valorAtual(5).valorMinimo(1).valorMaximo(10).sistema("cthulhu").ficha(null).build(),
                Atributo.builder().nome("Sanidade").valorAtual(5).valorMinimo(1).valorMaximo(10).sistema("cthulhu").ficha(null).build(),
                Atributo.builder().nome("Carisma").valorAtual(5).valorMinimo(1).valorMaximo(10).sistema("cthulhu").ficha(null).build(),
                Atributo.builder().nome("Força").valorAtual(10).valorMinimo(1).valorMaximo(15).sistema("ordem").ficha(null).build(),
                Atributo.builder().nome("Destreza").valorAtual(10).valorMinimo(1).valorMaximo(15).sistema("ordem").ficha(null).build(),
                Atributo.builder().nome("Inteligência").valorAtual(10).valorMinimo(1).valorMaximo(15).sistema("ordem").ficha(null).build(),
                Atributo.builder().nome("Energia").valorAtual(10).valorMinimo(1).valorMaximo(15).sistema("ordem").ficha(null).build(),
                Atributo.builder().nome("Carisma").valorAtual(10).valorMinimo(1).valorMaximo(15).sistema("ordem").ficha(null).build()
        );
        atributoRepository.saveAll(atributos);


        var itens = List.of(
                Item.builder().nome("Espada Longa").descricao("Dano 1d8").poder(8).sistema("dnd").build(),
                Item.builder().nome("Arco Curto").descricao("Dano 1d6").poder(6).sistema("dnd").build(),
                Item.builder().nome("Poção de Cura").descricao("Restaura vida").poder(0).sistema("dnd").build(),
                Item.builder().nome("Machado de Batalha").descricao("Dano 1d10").poder(10).sistema("tormenta").build(),
                Item.builder().nome("Armadura de Couro").descricao("Proteção leve").poder(0).sistema("tormenta").build(),
                Item.builder().nome("Poção de Força").descricao("Aumenta força temporariamente").poder(0).sistema("tormenta").build(),
                Item.builder().nome("Revólver").descricao("Dano 1d8").poder(8).sistema("cthulhu").build(),
                Item.builder().nome("Lanterna").descricao("Ilumina locais escuros").poder(0).sistema("cthulhu").build(),
                Item.builder().nome("Kit de Investigação").descricao("Ferramentas para pesquisa").poder(0).sistema("cthulhu").build(),
                Item.builder().nome("Pistola de Plasma").descricao("Dano 1d10").poder(10).sistema("ordem").build(),
                Item.builder().nome("Escudo Energético").descricao("Proteção temporária").poder(0).sistema("ordem").build(),
                Item.builder().nome("Amuleto de Luz").descricao("Aumenta energia").poder(0).sistema("ordem").build()
        );
        itemRepository.saveAll(itens);

        var habilidades = List.of(
                Habilidade.builder().nome("Bola de Fogo").descricao("Dano em área").custo(5).sistema("dnd").build(),
                Habilidade.builder().nome("Cura").descricao("Restaura pontos de vida").custo(3).sistema("dnd").build(),
                Habilidade.builder().nome("Furtividade").descricao("Evita ser detectado").custo(2).sistema("dnd").build(),
                Habilidade.builder().nome("Investida").descricao("Ataque rápido").custo(1).sistema("dnd").build(),
                Habilidade.builder().nome("Golpe Feroz").descricao("Dano físico alto").custo(2).sistema("tormenta").build(),
                Habilidade.builder().nome("Proteção Divina").descricao("Reduz dano").custo(3).sistema("tormenta").build(),
                Habilidade.builder().nome("Correr").descricao("Aumenta movimento").custo(1).sistema("tormenta").build(),
                Habilidade.builder().nome("Encantar").descricao("Aumenta carisma temporariamente").custo(2).sistema("tormenta").build(),
                Habilidade.builder().nome("Investigação").descricao("Descobre pistas").custo(1).sistema("cthulhu").build(),
                Habilidade.builder().nome("Conjurar Feitiço").descricao("Pequeno efeito mágico").custo(2).sistema("cthulhu").build(),
                Habilidade.builder().nome("Resistir ao Medo").descricao("Mantém sanidade").custo(3).sistema("cthulhu").build(),
                Habilidade.builder().nome("Persuasão").descricao("Influência social").custo(1).sistema("cthulhu").build(),
                Habilidade.builder().nome("Ataque Energético").descricao("Dano mágico").custo(5).sistema("ordem").build(),
                Habilidade.builder().nome("Escudo Protetor").descricao("Reduz dano recebido").custo(4).sistema("ordem").build(),
                Habilidade.builder().nome("Detecção Paranormal").descricao("Revela entidades ocultas").custo(2).sistema("ordem").build(),
                Habilidade.builder().nome("Fortalecer Aliado").descricao("Aumenta energia de outro personagem").custo(3).sistema("ordem").build()
        );
        habilidadeRepository.saveAll(habilidades);

                Sistema dnd = Sistema.builder().nome("dnd")
                        .racas(List.of("Humano", "Elfo", "Anão", "Halfling"))
                        .classes(List.of("Guerreiro", "Mago", "Ladino", "Clérigo"))
                        .vidaMin(5).vidaMax(100).manaMin(0).manaMax(50)
                        .build();

                Sistema tormenta = Sistema.builder().nome("tormenta")
                        .racas(List.of("Humano", "Elfo", "Anão", "Orc"))
                        .classes(List.of("Guerreiro", "Mago", "Paladino", "Clérigo"))
                        .vidaMin(10).vidaMax(120).manaMin(0).manaMax(80)
                        .build();

                Sistema cthulhu = Sistema.builder().nome("cthulhu")
                        .racas(List.of("Humano"))
                        .classes(List.of("Investigador", "Acadêmico", "Detetive"))
                        .vidaMin(5).vidaMax(30).manaMin(0).manaMax(10)
                        .build();

                Sistema ordem = Sistema.builder().nome("ordem")
                        .racas(List.of("Humano", "Psíquico"))
                        .classes(List.of("Místico", "Caçador", "Guarda"))
                        .vidaMin(10).vidaMax(70).manaMin(0).manaMax(40)
                        .build();

        sistemaRepository.saveAll(List.of(dnd, tormenta, cthulhu, ordem));

        dnd.setAtributos(atributoRepository.findBySistema("dnd"));
        dnd.setItens(itemRepository.findBySistema("dnd"));
        dnd.setHabilidades(habilidadeRepository.findBySistema("dnd"));
        sistemaRepository.save(dnd);

        tormenta.setAtributos(atributoRepository.findBySistema("tormenta"));
        tormenta.setItens(itemRepository.findBySistema("tormenta"));
        tormenta.setHabilidades(habilidadeRepository.findBySistema("tormenta"));
        sistemaRepository.save(tormenta);

        cthulhu.setAtributos(atributoRepository.findBySistema("cthulhu"));
        cthulhu.setItens(itemRepository.findBySistema("cthulhu"));
        cthulhu.setHabilidades(habilidadeRepository.findBySistema("cthulhu"));
        sistemaRepository.save(cthulhu);

        ordem.setAtributos(atributoRepository.findBySistema("ordem"));
        ordem.setItens(itemRepository.findBySistema("ordem"));
        ordem.setHabilidades(habilidadeRepository.findBySistema("ordem"));
        sistemaRepository.save(ordem);

    }

}
