package medtech.notification.medtech_notification.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import medtech.notification.medtech_notification.enums.StatusDaConsulta;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaCanceladaDTO(
        UUID medicoId,
        UUID pacienteId,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime dataConsulta,
        StatusDaConsulta status
) {
}
