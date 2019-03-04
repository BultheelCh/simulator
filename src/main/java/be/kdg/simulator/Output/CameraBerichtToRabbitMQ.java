package be.kdg.simulator.Output;

import be.kdg.simulator.model.CameraBericht;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//@Service
@Component
public class CameraBerichtToRabbitMQ implements OutputModusCameraBerichten {

    //opzetten van de logging
    private static final Logger log = LoggerFactory.getLogger(CameraBerichtToRabbitMQ.class);

    @Autowired
    private Environment env;

    private final RabbitTemplate rabbitTemplate;

    //Constructor
    public CameraBerichtToRabbitMQ(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //Verzenden van de bericht-objecten naar de queue
    //@Scheduled(fixedDelay = 3000L)
    //=> bericht wordt verzonden om de 3 seconden.
    @Override
    public void SendTo(CameraBericht cameraBericht) {
        rabbitTemplate.convertAndSend(env.getProperty("rabbitmq.exchangeName"), env.getProperty("rabbitmq.routingKey"), cameraBericht);
        log.info("Camerabericht verzonden! (" + cameraBericht.toString() + ")");
    }



}
