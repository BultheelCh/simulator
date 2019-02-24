package be.kdg.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Profile("!dev")
@Configuration
public class ApplicationConfiguration {

    @PostConstruct
    public void test(){
        System.out.println("Application Configuration loaded");
    }

    //Configuration RabbitMQ
    @Value("${jsa.rabbitmq.exchange}")
    public static final String ExchangeName = null;
    @Value("${jsa.rabbitmq.queue}")
    public static final String QueueName = null;
    @Value("${jsa.rabbitmq.routingkey}")
    public static final String RoutingKey=null;

    //Configuration import file
    @Value("${jsa.rabbitmq.routingkey}")
    public static final String FILENAME = null;


}
