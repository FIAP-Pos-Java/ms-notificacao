package medtech.notification.medtech_notification.mappers;

import medtech.notification.medtech_notification.dto.MedicoDTO;
import medtech.notification.medtech_notification.entities.Medico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
    MedicoDTO toMostrarMedicoDTO(Medico medico);
}
