package medtech.notification.medtech_notification.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE = "consultas.exchange";

    public static final String QUEUE_AGENDADA = "consulta.notificacao.agendada.queue";
    public static final String QUEUE_CANCELADA = "consulta.notificacao.cancelada.queue";

    public static final String ROUTING_KEY_AGENDADA = "consulta.notificacao.agendada";
    public static final String ROUTING_KEY_CANCELADA = "consulta.notificacao.cancelada";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE);
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
    public Binding bindingAgendada(Queue agendadaQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(agendadaQueue)
                .to(directExchange)
                .with(ROUTING_KEY_AGENDADA);
    }

    @Bean
    public Binding bindingCancelada(Queue canceladaQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(canceladaQueue)
                .to(directExchange)
                .with(ROUTING_KEY_CANCELADA);
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
