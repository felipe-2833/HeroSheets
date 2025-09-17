package br.com.fiap.herosheets.atributo;

import br.com.fiap.herosheets.sistema.Sistema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Atributo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int valorAtual;
    private int valorMinimo;
    private int valorMaximo;

    // Sistema ao qual pertence
    @ManyToOne
    private Sistema sistema;

}
