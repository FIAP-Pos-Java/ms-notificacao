package medtech.notification.medtech_notification.listener;

import medtech.notification.medtech_notification.configuration.RabbitMQConfig;
import medtech.notification.medtech_notification.dto.ConsultaAgendadaDTO;
import medtech.notification.medtech_notification.dto.ConsultaCanceladaDTO;
import medtech.notification.medtech_notification.enums.StatusDaConsulta;
import medtech.notification.medtech_notification.service.EmailService;
import medtech.notification.medtech_notification.service.MedicoService;
import medtech.notification.medtech_notification.service.PacienteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    private final EmailService emailService;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    public NotificationListener(EmailService emailService, PacienteService pacienteService, MedicoService medicoService) {
        this.emailService = emailService;
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_AGENDADA)
    public void processarMensagem(ConsultaAgendadaDTO dto) {
        System.out.println("Recebida mensagem de consulta agendada: " + dto);

        var paciente = pacienteService.buscarPacientePorId(dto.pacienteId());
        var medico = medicoService.buscarMedicoPorId(dto.medicoId());

        String assunto = "";
        String corpo = "";

        if (dto.status() == StatusDaConsulta.AGENDADA) {
            assunto = "Lembrete de Consulta - MedTech";
            corpo = String.format(
                    "Olá %s, sua consulta está marcada com o Dr(a). %s (%s).",
                    paciente.nome(),
                    medico.nome(),
                    medico.especialidade()
            );
        }
        else if (dto.status() == StatusDaConsulta.EDITADA) {
            assunto = "Alteração de Consulta - MedTech";
            corpo = String.format(
                    "Olá %s, sua consulta com o Dr(a). %s (%s) foi alterada. Por favor, verifique os novos detalhes.",
                    paciente.nome(),
                    medico.nome(),
                    medico.especialidade()
            );
        }

        emailService.enviarEmail(
                paciente.email(),
                assunto,
                corpo
        );

        System.out.println("E-mail enviado para " + paciente.email());
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CANCELADA)
    public void processarMensagemCancelada(ConsultaCanceladaDTO dto) {
        System.out.println("Recebida mensagem de consulta cancelada: " + dto);

        var paciente = pacienteService.buscarPacientePorId(dto.pacienteId());
        var medico = medicoService.buscarMedicoPorId(dto.medicoId());

        String assunto = "Cancelamento de Consulta - MedTech";
        String corpo = String.format(
                "Olá %s, sua consulta com o Dr(a). %s (%s) foi cancelada. Entre em contato para mais informações.",
                paciente.nome(),
                medico.nome(),
                medico.especialidade()
        );

        emailService.enviarEmail(
                paciente.email(),
                assunto,
                corpo
        );

        System.out.println("E-mail enviado para " + paciente.email());
    }
}