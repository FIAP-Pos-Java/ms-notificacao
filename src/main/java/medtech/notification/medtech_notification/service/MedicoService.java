package medtech.notification.medtech_notification.service;

import medtech.notification.medtech_notification.dto.MedicoDTO;
import medtech.notification.medtech_notification.exceptions.UsuarioNaoEncontradoException;
import medtech.notification.medtech_notification.mappers.MedicoMapper;
import medtech.notification.medtech_notification.repositories.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;

    public MedicoService(MedicoRepository medicoRepository, MedicoMapper medicoMapper) {
        this.medicoRepository = medicoRepository;
        this.medicoMapper = medicoMapper;
    }

    private final String MESSAGE_MEDICO_NAO_ENCONTRADO = "Médico não encontrado";

    public MedicoDTO buscarMedicoPorId(String idStr){
        UUID id = UUID.fromString(idStr);
        System.out.println("Buscando médico com ID: " + id);
        var buscarMedico = this.medicoRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(MESSAGE_MEDICO_NAO_ENCONTRADO));

        System.out.println("Médico encontrado: " + buscarMedico.getEmail() + " - " + buscarMedico.getNome());

        return this.medicoMapper.toMostrarMedicoDTO(buscarMedico);
    }
}
