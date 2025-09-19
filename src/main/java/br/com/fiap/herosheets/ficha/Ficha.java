package br.com.fiap.herosheets.ficha;

import br.com.fiap.herosheets.atributo.Atributo;
import br.com.fiap.herosheets.campanha.Campanha;
import br.com.fiap.herosheets.habilidade.Habilidade;
import br.com.fiap.herosheets.item.Item;
import br.com.fiap.herosheets.sistema.Sistema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "{sheet.name.notblank}")
    private String nome;
    @NotBlank(message = "{sheet.raca.notblank}")
    private String raca;
    @NotBlank(message = "{sheet.classe.notblank}")
    private String classe;
    @Min(value = 1, message = "{sheet.nivel.min}")
    @Max(value = 100, message = "{sheet.nivel.max}")
    private int nivel;

    @OneToMany(mappedBy = "ficha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atributo> atributos;

    @Transient
    private List<Long> atributoIds;

    @ManyToMany
    @JoinTable(
            name = "ficha_inventario",
            joinColumns = @JoinColumn(name = "ficha_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> inventario;

    @Transient
    private List<Long> inventarioIds;

    @ManyToMany
    @JoinTable(
            name = "ficha_habilidades",
            joinColumns = @JoinColumn(name = "ficha_id"),
            inverseJoinColumns = @JoinColumn(name = "habilidade_id")
    )
    private List<Habilidade> habilidades;

    @Transient
    private List<Long> habilidadeIds;

    private int vida;
    private int mana;

    @ManyToOne
    private Campanha campanha;

}
