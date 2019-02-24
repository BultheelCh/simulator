package be.kdg.processor;

import be.kdg.simulator.CameraBericht;
import be.kdg.simulator.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    @Value("${rabbitmq.queueName}")
    public final String queueName="";


    @RabbitListener(queues="queue_cameraberichten")
    public void receivedMessage(CameraBericht bericht){
       log.info("Ontvangen bericht op queue({}): {}", "queue_cameraberichten",  bericht.toString());
    }


}
