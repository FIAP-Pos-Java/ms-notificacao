package medtech.notification.medtech_notification.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_medico")
public final class Medico extends Usuario {
    private String crm;
    private String especialidade;

    public Medico() {}

    public Medico(UUID id, String nome, String email, LocalDate dataNascimento, LocalDateTime dataCadastro, String telefone, boolean enabled, String crm, String especialidade) {
        super(id, nome, email, dataNascimento, dataCadastro, telefone, enabled);
        this.crm = crm;
        this.especialidade = especialidade;
    }
}
