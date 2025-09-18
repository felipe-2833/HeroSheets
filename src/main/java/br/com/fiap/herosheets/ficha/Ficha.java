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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Atributo> atributos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> inventario;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Habilidade> habilidades;

    @Min(value = 0, message = "{sheet.vida.min}")
    @Max(value = 100, message = "{sheet.vida.max}")
    private int vida;
    @Min(value = 0, message = "{sheet.mana.min}")
    @Max(value = 100, message = "{sheet.mana.max}")
    private int mana;

    @ManyToOne
    private Campanha campanha;

}
