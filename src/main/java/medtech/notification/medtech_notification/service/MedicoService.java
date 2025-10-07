package medtech.notification.medtech_notification.service;

import lombok.RequiredArgsConstructor;
import medtech.notification.medtech_notification.dto.MedicoDTO;
import medtech.notification.medtech_notification.exceptions.UsuarioNaoEncontradoException;
import medtech.notification.medtech_notification.mappers.MedicoMapper;
import medtech.notification.medtech_notification.repositories.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;

    private final String MESSAGE_MEDICO_NAO_ENCONTRADO = "Médico não encontrado";

    public MedicoDTO buscarMedicoPorId(String idStr){
        UUID id = UUID.fromString(idStr);
        var buscarMedico = this.medicoRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(MESSAGE_MEDICO_NAO_ENCONTRADO));

        return this.medicoMapper.toMostrarMedicoDTO(buscarMedico);
    }
}
