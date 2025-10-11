package medtech.notification.medtech_notification.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_AGENDAMENTO = "agendamento.exchange";
    public static final String EXCHANGE_CANCELAMENTO = "cancelamento.exchange";

    public static final String QUEUE_AGENDADA = "consulta.notificacao.agendada.queue";
    public static final String QUEUE_CANCELADA = "consulta.notificacao.cancelada.queue";

    @Bean
    public FanoutExchange agendamentosExchange() {
        return new FanoutExchange(EXCHANGE_AGENDAMENTO);
    }

    @Bean
    public FanoutExchange cancelamentosExchange() {
        return new FanoutExchange(EXCHANGE_CANCELAMENTO);
    }

    @Bean
    public Queue agendadaQueue() {
        return new Queue(QUEUE_AGENDADA, true);
    }

    @Bean
    public Queue canceladaQueue() {
        return new Queue(QUEUE_CANCELADA, true);
    }

    @Bean
    public Binding bindingAgendada(Queue agendadaQueue, FanoutExchange agendamentosExchange) {
        return BindingBuilder.bind(agendadaQueue).to(agendamentosExchange);
    }

    @Bean
    public Binding bindingCancelada(Queue canceladaQueue, FanoutExchange cancelamentosExchange) {
        return BindingBuilder.bind(canceladaQueue).to(cancelamentosExchange);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
