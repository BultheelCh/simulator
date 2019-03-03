package be.kdg.processor;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProcessorApplication {

    public static void main(String[] args)
    {
        //initialisatie van de spring container
        //ConfigurableApplicationContext context =  SpringApplication.run(ProcessorApplication.class, args);
        //spring boot maakt object aan in container volgens het singleton pattern
        SpringApplication.run(ProcessorApplication.class, args);
        //c = context.getBean(Consumer.class);
    }



    //Variabelen koppelen met Configuration file (application.properties)

    @Value("${rabbitmq.exchangeName}")
    private  String echangeName;
    @Value("${rabbitmq.queueName}")
    private String queueName;
    @Value("${rabbitmq.routingKey}")
    private String routingKey;
    @Value("${importPath}")
    private  String fileName;



    //static final String queueName = "spring-boot";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(echangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
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

/*    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }*/
/*
    @Bean
    MessageListenerAdapter listenerAdapter(CameraBericht cameraBericht) {
        return new MessageListenerAdapter(rabbitMQ, "ReadCameraBericht");
    }*/
/*
    //Configuratie van RabbitMQ
    //1) aanmaken van een nieuwe exchange
    @Bean
    public TopicExchange cameraBerichtenExchange(){
        //return new TopicExchange(EXCHANGE_NAME);
        return new TopicExchange(echangeName);
    }

    //2) aanmaken van een queue
    @Bean
    public Queue parsingQueue(){
        return new Queue(queueName);
    }

    //3) koppeling van de queue en de exchange
    @Bean
    public Binding queueToExchangeBinding(){
        return BindingBuilder.bind(parsingQueue()).to(cameraBerichtenExchange()).with(routingKey);
    }*/

}
