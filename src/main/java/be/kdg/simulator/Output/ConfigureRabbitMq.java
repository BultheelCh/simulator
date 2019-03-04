package be.kdg.simulator.Output;

import be.kdg.simulator.RabbitMqProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigureRabbitMq {

    @Autowired
    RabbitMqProperties rabbitMqProperties;

//Configuratie van RabbitMQ
//1) aanmaken van een nieuwe exchange
        @Bean
        public TopicExchange cameraBerichtenExchange(){
            //return new TopicExchange(EXCHANGE_NAME);
            System.out.println(rabbitMqProperties.getExchangeName());
            return new TopicExchange(rabbitMqProperties.getExchangeName());
        }

//2) aanmaken van een queue
        @Bean
        public Queue parsingQueue(){
            System.out.println(rabbitMqProperties.getQueueName());
            return new Queue(rabbitMqProperties.getQueueName());
        }

//3) koppeling van de queue en de exchange
        @Bean
        public Binding queueToExchangeBinding(){
            return BindingBuilder.bind(parsingQueue()).to(cameraBerichtenExchange()).with(rabbitMqProperties.getRoutingKey());
        }

        @Bean
        public Jackson2JsonMessageConverter producerMessageConverter(){
            return new Jackson2JsonMessageConverter();
        }

        @Bean
        public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setMessageConverter(producerMessageConverter());
            return rabbitTemplate;
        }
    }

