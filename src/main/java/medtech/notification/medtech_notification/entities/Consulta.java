package medtech.notification.medtech_notification.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import medtech.notification.medtech_notification.enums.StatusDaConsulta;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="consultas")
public class Consulta {

    @Id
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusDaConsulta status;

    @Column(nullable = false)
    private UUID medicoId;

    @Column(nullable = false)
    private UUID pacienteId;
}
