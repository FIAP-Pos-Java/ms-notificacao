package medtech.notification.medtech_notification.service;

import lombok.RequiredArgsConstructor;
import medtech.notification.medtech_notification.dto.PacienteDTO;
import medtech.notification.medtech_notification.exceptions.UsuarioNaoEncontradoException;
import medtech.notification.medtech_notification.mappers.PacienteMapper;
import medtech.notification.medtech_notification.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    private final String MESSAGE_PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado";

    public PacienteDTO buscarPacientePorId(String idStr){
        UUID id = UUID.fromString(idStr);
        var buscarPaciente = this.pacienteRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(MESSAGE_PACIENTE_NAO_ENCONTRADO));

        return this.pacienteMapper.toMostrarPacienteDTO(buscarPaciente);
    }
}