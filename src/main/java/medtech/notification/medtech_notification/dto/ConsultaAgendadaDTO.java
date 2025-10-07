package medtech.notification.medtech_notification.dto;

public record ConsultaAgendadaDTO(
    Long consultaId,
    Long pacienteId,
    Long medicoId,
    String medicoNome,
    String especialidade,
    String nomePaciente,
    String emailPaciente
) {
}
