package be.kdg.simulator;

import be.kdg.simulator.Input.RandomModus;
import be.kdg.simulator.model.CameraBerichten;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Profile("!dev")

//@Configuration
//@ComponentScan(basePackages = "be.kdg.simulator")
//@PropertySource("classpath:application.properties")
public class ApplicationConfiguration {

    @PostConstruct
    public void test(){
        System.out.println("Application Configuration loading");
    }

/*    @Bean
    public CameraBerichtToRabbitMQ getRabbitMQ(){
        return new CameraBerichtToRabbitMQ();
    }*/

    @Bean
    public CameraBerichten getCameraBerichten(){
        return new CameraBerichten();
    }

    @Bean
    public RandomModus getRandomModus(){
        return new RandomModus();
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Application Configuration destroy");
        //System.out.println(delay);
    }

/*    //Configuratie van RabbitMQ
    //1) aanmaken van een nieuwe exchange
    @Bean
    public TopicExchange cameraBerichtenExchange(){
        //return new TopicExchange(EXCHANGE_NAME);
        return new TopicExchange(exchangeName);
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
    }*/

}
