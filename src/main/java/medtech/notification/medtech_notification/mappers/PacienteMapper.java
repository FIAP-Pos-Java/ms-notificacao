package medtech.notification.medtech_notification.mappers;

import medtech.notification.medtech_notification.dto.PacienteDTO;
import medtech.notification.medtech_notification.entities.Paciente;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {
    public PacienteDTO toMostrarPacienteDTO(Paciente paciente) {
        if (paciente == null) {
            return null;
        }

        return new PacienteDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getLogin().getEmail(),
                paciente.getDataNascimento(),
                paciente.getDataCadastro(),
                paciente.getTelefone(),
                paciente.isEnabled()
        );
    }
}
