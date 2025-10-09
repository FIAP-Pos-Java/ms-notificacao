package medtech.notification.medtech_notification.service;

import medtech.notification.medtech_notification.dto.PacienteDTO;
import medtech.notification.medtech_notification.exceptions.UsuarioNaoEncontradoException;
import medtech.notification.medtech_notification.mappers.PacienteMapper;
import medtech.notification.medtech_notification.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.pacienteMapper = pacienteMapper;
    }

    private final String MESSAGE_PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado";

    public PacienteDTO buscarPacientePorId(UUID id){
        System.out.println("Buscando paciente com ID: " + id);
        var buscarPaciente = this.pacienteRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(MESSAGE_PACIENTE_NAO_ENCONTRADO));

        System.out.println("Paciente encontrado:" + buscarPaciente.getLogin().getEmail() + " - " + buscarPaciente.getNome());

        return this.pacienteMapper.toMostrarPacienteDTO(buscarPaciente);
    }
}