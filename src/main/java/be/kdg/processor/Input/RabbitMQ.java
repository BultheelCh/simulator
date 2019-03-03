package be.kdg.processor.Input;


import be.kdg.simulator.model.CameraBericht;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


//@Component
@Service
public class RabbitMQ  {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQ.class);


//   @Value("${rabbitmq.queueName}")
//    public final String queueName="";

    @RabbitListener(queues = "queue_cameraberichten" )
    public void ReadCameraCamera(final CameraBericht cameraBericht){
        log.info("Received message with default configuration: {}", cameraBericht.toString());
    }

/*    @RabbitListener(queues="queue_cameraberichten")
    public void receivedMessage(final CameraBericht bericht){
        try{
            System.out.println(bericht);
        }
        catch ( Exception ex){
            System.out.println(ex.getMessage());
        }*/

        //System.out.println(bericht);
       //log.info("Ontvangen bericht op queue({}): {}", "queue_cameraberichten",  bericht.toString());
    //}


/*    @RabbitListener(queues="queue_cameraberichten")
    //@RabbitHandler
    public void ReadCameraBericht(String cameraBericht) {
        log.info("Ontvangen bericht op queue({}): {}", "queue_cameraberichten",  cameraBericht.toString());
    }*/


}

