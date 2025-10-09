package medtech.notification.medtech_notification.service;

import medtech.notification.medtech_notification.dto.ConsultaDTO;
import medtech.notification.medtech_notification.exceptions.UsuarioNaoEncontradoException;
import medtech.notification.medtech_notification.repositories.ConsultaRepository;
import medtech.notification.medtech_notification.repositories.MedicoRepository;
import medtech.notification.medtech_notification.repositories.PacienteRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    private final String MESSAGE_PACIENTE_NAO_ENCONTRADO = "Paciente não encontrado";
    private final String MESSAGE_MEDICO_NAO_ENCONTRADO = "Médico não encontrado";

    public EmailService(JavaMailSender mailSender,
                        ConsultaRepository consultaRepository,
                        PacienteRepository pacienteRepository,
                        MedicoRepository medicoRepository) {
        this.mailSender = mailSender;
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
    }

    public void enviarEmail(String para, String assunto, String corpo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(para);
        message.setSubject(assunto);
        message.setText(corpo);
        mailSender.send(message);
    }

    // envia as notificações diariamente às 10h, sobre as consultas do dia seguinte, para fins de teste deixei 5 minutos
    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(cron = "0 */5 * * * *")
    public void enviarLembretes() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime daquiUmDia = agora.plusDays(1);

        List<ConsultaDTO> consultas = consultaRepository.findByDataBetween(agora, daquiUmDia);

        for (ConsultaDTO consulta : consultas) {
            var paciente = this.pacienteRepository.findById(consulta.pacienteId()).orElseThrow(() -> new UsuarioNaoEncontradoException(MESSAGE_PACIENTE_NAO_ENCONTRADO));
            var medico = this.medicoRepository.findById(consulta.medicoId()).orElseThrow(() -> new UsuarioNaoEncontradoException(MESSAGE_MEDICO_NAO_ENCONTRADO));

            String assunto = "Lembrete de Consulta";
            String texto = String.format("Olá %s, você tem uma consulta com o Dr(a) %s (%s) amanhã.",
                    paciente.getNome(),
                    medico.getNome(),
                    medico.getEspecialidade());

            enviarEmail(paciente.getLogin().getEmail(), assunto, texto);
        }
    }
}
