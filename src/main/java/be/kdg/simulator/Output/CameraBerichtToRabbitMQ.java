package be.kdg.simulator.Output;

import be.kdg.simulator.Configuration.RabbitMqProperties;
import be.kdg.simulator.model.CameraBericht;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CameraBerichtToRabbitMQ implements CameraMessageSender {

    private static final Logger log = LoggerFactory.getLogger(CameraBerichtToRabbitMQ.class);

    @Autowired
    private Environment env;
    @Autowired
    private RabbitMqProperties rabbitMqProperties;

    private final RabbitTemplate rabbitTemplate;

    //Constructor
    public CameraBerichtToRabbitMQ(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //Verzenden van de bericht-objecten naar de queue
    //@Scheduled(fixedDelay = 3000L)
    //=> bericht wordt verzonden om de 3 seconden.
    @Override
    public void Send(CameraBericht cameraBericht) {
        rabbitTemplate.convertAndSend(rabbitMqProperties.getExchangeName() , rabbitMqProperties.getRoutingKey() , cameraBericht);
        log.info("Camerabericht verzonden! (" + cameraBericht.toString() + ")");
    }


}



