package be.kdg.simulator.Output;

import be.kdg.simulator.OutputModusCameraBerichten;
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

//    @Autowired
//    CameraBerichtToRabbitMQ cameraBerichtToRabbitMQ;

    //Constructor
    public CameraBerichtToRabbitMQ(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    //Variabelen koppelen met Configuration file (application.properties)
/*    @Value("${rabbitmq.exchangeName}")
    private  String exchangeName;
    @Value("${rabbitmq.queueName}")
    private String queueName;
    @Value("${rabbitmq.routingKey}")
    private String routingKey;*/


    //Verzenden van de bericht-objecten naar de queue
    //@Scheduled(fixedDelay = 3000L)
    //=> bericht wordt verzonden om de 3 seconden.
    //@Override
    public void SendTo(CameraBericht cameraBericht) {
        //CameraBericht cameraBericht = new CameraBericht(1, new Timestamp(System.currentTimeMillis()) , "1-NEN-401");
        //CameraBericht cameraBericht = new CameraBericht( );
        //cameraBericht.setCameraInputModus(new RandomModus());
        //cameraBericht = cameraBericht.CreateCameraBericht();
        //cameraBericht.SendCameraBericht(  cameraBericht.CreateCameraBericht());
        rabbitTemplate.convertAndSend(env.getProperty("rabbitmq.exchangeName"), env.getProperty("rabbitmq.routingKey"), cameraBericht);
        //rabbitTemplate.convertAndSend(exchangeName, routingKey, cameraBericht);
        log.info("Camerabericht verzonden! (" + cameraBericht.toString() + ")");
//        try {
//            //stel vertraging in
//            //Thread.sleep(cameraBericht.getDelay());
//            //CameraBericht bericht = generator.CreateRandomCameraBericht(1000);
//            //CameraBericht bericht = generator.selectBerichtenModus(Modus.Random, 1000, "c:\\temp\\KDG\\SoftwareArchitecture\\input.csv", ","  );
//            if (cameraBericht == null) {
//                log.info("Camerabericht niet verzonden!");
//                return;
//            }
//            //doorsturen naar de queue
//            rabbitTemplate.convertAndSend(exchangeName, routingKey, cameraBericht);
//            log.info("Camerabericht verzonden! (" + cameraBericht.toString() + ")");
//        }
//        catch (Exception ex){
//            System.out.println(ex.getMessage());
//        }
    }



}
