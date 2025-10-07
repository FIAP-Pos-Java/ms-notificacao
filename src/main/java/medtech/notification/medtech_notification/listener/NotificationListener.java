package medtech.notification.medtech_notification.listener;

import medtech.notification.medtech_notification.configuration.RabbitMQConfig;
import medtech.notification.medtech_notification.dto.ConsultaAgendadaDTO;
import medtech.notification.medtech_notification.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.Console;

@Component
public class NotificationListener {
    private final EmailService emailService;

    public NotificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_AGENDADA)
    public void processarMensagem(ConsultaAgendadaDTO mensagem) {

        // log pra ver se chegou aqui
        System.out.println("Recebida mensagem de consulta agendada: " + mensagem);
        String assunto = "Lembrete de Consulta";
        String corpo = String.format(
                "Olá %s, sua consulta está marcada com o Dr(a). %s (%s).",
                mensagem.nomePaciente(),
                mensagem.medicoNome(),
                mensagem.especialidade()
        );

        emailService.enviarEmail(
                mensagem.emailPaciente(),
                assunto,
                corpo
        );

        System.out.println("E-mail enviado para " + mensagem.emailPaciente());
    }
}