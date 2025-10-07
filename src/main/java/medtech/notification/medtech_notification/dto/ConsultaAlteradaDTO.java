package medtech.notification.medtech_notification.dto;

import java.time.LocalDateTime;

public record ConsultaAlteradaDTO(
    String pacienteId,
    String medicoId,
    LocalDateTime dataHora
) {
}
