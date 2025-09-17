package br.com.fiap.herosheets.campanha;

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
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String master;
    private int qtdPlayers;
    @ManyToOne
    private Sistema sistema;
}
