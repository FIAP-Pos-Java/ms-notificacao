package medtech.notification.medtech_notification.mappers;

import medtech.notification.medtech_notification.dto.MedicoDTO;
import medtech.notification.medtech_notification.entities.Medico;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MedicoMapper {
    public MedicoDTO toMostrarMedicoDTO(Medico medico) {
        if (medico == null) {
            return null;
        }

        return new MedicoDTO(
                medico.getId(),
                medico.getNome(),
                medico.getDataNascimento(),
                medico.getDataCadastro(),
                medico.getTelefone(),
                medico.isEnabled(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }
}
