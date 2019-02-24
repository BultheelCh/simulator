package be.kdg.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
//@Component
public class Producer {
   //opzetten van de logging
   private static final Logger log = LoggerFactory.getLogger(Producer.class);

   private final RabbitTemplate rabbitTemplate;

   @Autowired
   private BerichtenGenerator generator;

    //Variabelen koppelen met Configuration file (application.properties)
    @Value("${rabbitmq.exchangeName}")
    private  String exchangeName;
    @Value("${rabbitmq.queueName}")
    private String queueName;
    @Value("${rabbitmq.routingKey}")
    private String routingKey;

   //Constructor
    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    //Verzenden van de bericht-objecten naar de queue
    //@Scheduled(fixedDelay = 3000L) => bericht wordt verzonden om de 3 seconden.
    public void SendMessage(CameraBericht cameraBericht, boolean onConsole){
        try {

            //stel vertraging in
            Thread.sleep(cameraBericht.getDelay());
            //CameraBericht bericht = generator.CreateRandomCameraBericht(1000);
            //CameraBericht bericht = generator.selectBerichtenModus(Modus.Random, 1000, "c:\\temp\\KDG\\SoftwareArchitecture\\input.csv", ","  );
            if (cameraBericht == null) {
                log.info("Camerabericht niet verzonden!");
                return;
            }
            //berichten naar de console
            if (onConsole){
                System.out.println(cameraBericht.toString());
                return;
            }
            //doorsturen naar de queue
            rabbitTemplate.convertAndSend(exchangeName, routingKey, cameraBericht);
            log.info("Camerabericht verzonden! (" + cameraBericht.toString() + ")");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
