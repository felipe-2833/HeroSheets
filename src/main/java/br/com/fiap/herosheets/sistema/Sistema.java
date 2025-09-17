package br.com.fiap.herosheets.sistema;

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

    private int vidaMin;
    private int vidaMax;
    private int manaMin;
    private int manaMax;

    @ElementCollection
    private List<String> atributos;
    @ElementCollection
    private List<Integer> valoresIniciais; // valor inicial de cada atributo
    @ElementCollection
    private List<Integer> valoresMinimos;
    @ElementCollection
    private List<Integer> valoresMaximos;

}
