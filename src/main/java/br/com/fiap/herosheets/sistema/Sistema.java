package br.com.fiap.herosheets.sistema;

import br.com.fiap.herosheets.atributo.Atributo;
import br.com.fiap.herosheets.habilidade.Habilidade;
import br.com.fiap.herosheets.item.Item;
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
public class Sistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ElementCollection
    private List<String> racas;

    @ElementCollection
    private List<String> classes;

    @OneToMany(mappedBy = "sistema", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atributo> atributos;

    @OneToMany(mappedBy = "sistema", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens;

    @OneToMany(mappedBy = "sistema", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habilidade> habilidades;

    private int vidaMin;
    private int vidaMax;
    private int manaMin;
    private int manaMax;


}
