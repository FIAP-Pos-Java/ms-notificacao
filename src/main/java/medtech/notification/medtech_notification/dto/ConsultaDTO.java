package medtech.notification.medtech_notification.dto;

import medtech.notification.medtech_notification.enums.StatusDaConsulta;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaDTO(
        UUID id,
        LocalDateTime dataHora,
        StatusDaConsulta status,
        UUID medicoId,
        UUID pacienteId
) { }
