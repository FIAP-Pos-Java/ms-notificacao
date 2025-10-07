package medtech.notification.medtech_notification.listener;

import lombok.RequiredArgsConstructor;
import medtech.notification.medtech_notification.configuration.RabbitMQConfig;
import medtech.notification.medtech_notification.dto.ConsultaAgendadaDTO;
import medtech.notification.medtech_notification.dto.ConsultaCanceladaDTO;
import medtech.notification.medtech_notification.dto.ConsultaAlteradaDTO;
import medtech.notification.medtech_notification.service.EmailService;
import medtech.notification.medtech_notification.service.MedicoService;
import medtech.notification.medtech_notification.service.PacienteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {
    private final EmailService emailService;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_AGENDADA)
    public void processarMensagem(ConsultaAgendadaDTO mensagem) {
        System.out.println("Recebida mensagem de consulta agendada: " + mensagem);

        var paciente = pacienteService.buscarPacientePorId(mensagem.pacienteId());
        var medico = medicoService.buscarMedicoPorId(mensagem.medicoId());

        String assunto = "Lembrete de Consulta - MedTech";
        String corpo = String.format(
                "Olá %s, sua consulta está marcada com o Dr(a). %s (%s).",
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

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ALTERADA)
    public void processarMensagemAlterada(ConsultaAlteradaDTO mensagem) {
        System.out.println("Recebida mensagem de consulta alterada: " + mensagem);

        var paciente = pacienteService.buscarPacientePorId(mensagem.pacienteId());
        var medico = medicoService.buscarMedicoPorId(mensagem.medicoId());

        String assunto = "Atualização de Consulta - MedTech";
        String corpo = String.format(
                "Olá %s, sua consulta com o Dr(a). %s (%s) foi alterada. Por favor, verifique os novos detalhes.",
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

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CANCELADA)
    public void processarMensagemCancelada(ConsultaCanceladaDTO mensagem) {
        System.out.println("Recebida mensagem de consulta cancelada: " + mensagem);

        var paciente = pacienteService.buscarPacientePorId(mensagem.pacienteId());
        var medico = medicoService.buscarMedicoPorId(mensagem.medicoId());

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