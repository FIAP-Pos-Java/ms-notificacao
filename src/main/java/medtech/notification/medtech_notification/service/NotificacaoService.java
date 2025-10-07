package medtech.notification.medtech_notification.service;

import medtech.notification.medtech_notification.configuration.RabbitMQConfig;
import medtech.notification.medtech_notification.dto.ConsultaAgendadaDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {
    private final RabbitTemplate rabbitTemplate;

    public NotificacaoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarNotificacaoConsultaAgendada(ConsultaAgendadaDTO message) {
        System.out.println("Enviando mensagem para fila de consulta agendada: " + message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY_AGENDADA, message);
    }

    public void enviarNotificacaoConsultaAlterada(ConsultaAgendadaDTO message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY_ALTERADA, message);
    }

    public void enviarNotificacaoConsultaCancelada(ConsultaAgendadaDTO message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY_CANCELADA, message);
    }
}
