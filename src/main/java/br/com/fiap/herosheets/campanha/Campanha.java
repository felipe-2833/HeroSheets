package br.com.fiap.herosheets.campanha;

import br.com.fiap.herosheets.sistema.Sistema;
import br.com.fiap.herosheets.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "{campanha.name.notblank}")
    private String name;

    @NotBlank(message = "{campanha.master.notblank}")
    private String master;

    @Min(value = 0, message = "{campanha.qtdPlayers.min}")
    @Max(value = 100, message = "{campanha.qtdPlayers.max}")
    private int qtdPlayers;

    @ManyToOne
    @NotNull(message = "{campanha.sistema.notnull}")
    private Sistema sistema;

    @ManyToOne
    private User user;
}
