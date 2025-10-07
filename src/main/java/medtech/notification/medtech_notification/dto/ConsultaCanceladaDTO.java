package medtech.notification.medtech_notification.dto;

public record ConsultaCanceladaDTO(
    String consultaId,
    String pacienteNome,
    String pacienteEmail,
    String medicoNome,
    String medicoEspecialidade,
    String dataHora,
    String motivoCancelamento
) {
}
