package medtech.notification.medtech_notification.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record PacienteDTO(
        UUID id,
        String nome,
        String email,
        LocalDate dataNascimento,
        LocalDateTime dataCadastro,
        String telefone,
        boolean enabled
) {}
