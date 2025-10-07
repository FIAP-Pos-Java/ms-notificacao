package medtech.notification.medtech_notification.mappers;

import medtech.notification.medtech_notification.dto.PacienteDTO;
import medtech.notification.medtech_notification.entities.Paciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
    PacienteDTO toMostrarPacienteDTO(Paciente paciente);
}
