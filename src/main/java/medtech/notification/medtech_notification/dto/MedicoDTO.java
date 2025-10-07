package medtech.notification.medtech_notification.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record MedicoDTO(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        LocalDateTime dataCadastro,
        String telefone,
        boolean enabled,
        String crm,
        String especialidade
) {}