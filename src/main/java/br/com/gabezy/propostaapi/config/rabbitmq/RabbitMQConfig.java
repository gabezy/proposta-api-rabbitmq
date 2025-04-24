package br.com.gabezy.propostaapi.config.rabbitmq;

import br.com.gabezy.propostaapi.config.properties.PropostaApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final PropostaApiProperties properties;

    @Bean
    public Queue createQueuePropostaPendenteMsAnaliseCredito() {
        return QueueBuilder.durable(properties.getQueueName().getPropostaPendenteAnaliseCredito())
                .deadLetterExchange(properties.getExchangeName().getPropostaPendenteDeadLetter())
                .build();
    }

    @Bean
    public Queue createQueuePropostaPendenteMsNotificao() {
        return QueueBuilder.durable(properties.getQueueName().getPropostaPendenteNotificacao()).build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMsProposta() {
        return QueueBuilder.durable(properties.getQueueName().getPropostaConcluidaProposta()).build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMsNotificao() {
        return QueueBuilder.durable(properties.getQueueName().getPropostaConcluidaNotificacao()).build();
    }

    @Bean
    public Queue createaQueuePropostaPendenteDeadLetter() {
        return QueueBuilder.durable(properties.getQueueName().getPropostaPendenteDeadLetter()).build();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
        // This will initialize the RabbitMQ admin and create the queues if they do not exist
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange fanoutExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange(properties.getExchangeName().getPropostaPendente()).durable(true).build();
    }

    @Bean
    public FanoutExchange fanoutExchangePropostaConcluida() {
        return ExchangeBuilder.fanoutExchange(properties.getExchangeName().getPropostaConcluida()).durable(true).build();
    }

    @Bean
    public FanoutExchange deadLetterExchangeFanout() {
        return ExchangeBuilder.fanoutExchange(properties.getExchangeName().getPropostaPendenteDeadLetter()).durable(true).build();
    }

    @Bean
    public Binding bindingPropostaPendenteMsAnaliseCredito() {
        return BindingBuilder.bind(createQueuePropostaPendenteMsAnaliseCredito())
                .to(fanoutExchangePropostaPendente());
    }

    @Bean
    public Binding bindingPropostaPendenteMsNotificao() {
        return BindingBuilder.bind(createQueuePropostaPendenteMsNotificao())
                .to(fanoutExchangePropostaPendente());
    }

    @Bean
    public Binding bindingPropostaConcluidaMsProposta() {
        return BindingBuilder.bind(createQueuePropostaConcluidaMsProposta())
                .to(fanoutExchangePropostaConcluida());
    }

    @Bean
    public Binding bindingPropostaConcluidaMsNotificao() {
        return BindingBuilder.bind(createQueuePropostaConcluidaMsNotificao())
                .to(fanoutExchangePropostaConcluida());
    }

    @Bean
    public Binding bindingPropostaPendenteDeadLetter() {
        return BindingBuilder.bind(createaQueuePropostaPendenteDeadLetter())
                .to(deadLetterExchangeFanout());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
