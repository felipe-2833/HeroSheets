package br.com.fiap.herosheets.ficha;

import br.com.fiap.herosheets.atributo.Atributo;
import br.com.fiap.herosheets.campanha.Campanha;
import br.com.fiap.herosheets.habilidade.Habilidade;
import br.com.fiap.herosheets.item.Item;
import br.com.fiap.herosheets.sistema.Sistema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ficha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String raca;
    private String classe;
    private int nivel;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Atributo> atributos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> inventario;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Habilidade> habilidades;

    private int vida;
    private int mana;

    @ManyToOne
    private Campanha campanha;

    public Ficha(String nome, String raca, String classe, int nivel,
                 int vida, int mana, Campanha campanha) {

        Sistema sistema = campanha.getSistema();

        // validações automáticas
        if (!sistema.getRacas().contains(raca)) {
            throw new IllegalArgumentException("Raça inválida para este sistema!");
        }
        if (!sistema.getClasses().contains(classe)) {
            throw new IllegalArgumentException("Classe inválida para este sistema!");
        }
        if (vida < sistema.getVidaMin() || vida > sistema.getVidaMax()) {
            throw new IllegalArgumentException("Vida fora dos limites do sistema!");
        }
        if (mana < sistema.getManaMin() || mana > sistema.getManaMax()) {
            throw new IllegalArgumentException("Mana fora dos limites do sistema!");
        }

        this.nome = nome;
        this.raca = raca;
        this.classe = classe;
        this.nivel = nivel;
        this.vida = vida;
        this.mana = mana;
        this.campanha = campanha;

        // Inicializar listas vazias para atributos, itens e habilidades
        this.atributos = List.copyOf(sistema.getAtributos());
        this.inventario = List.copyOf(sistema.getItens());
        this.habilidades = List.copyOf(sistema.getHabilidades());
    }


}
