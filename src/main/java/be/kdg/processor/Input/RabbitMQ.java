package be.kdg.processor.Input;


import be.kdg.processor.Business.Boetes;
import be.kdg.processor.Business.CameraBerichten;
import be.kdg.processor.model.CameraBericht;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("be.kdg.processor.Service")
public class RabbitMQ  {


    private static final Logger log = LoggerFactory.getLogger(RabbitMQ.class);

    @Autowired
    Boetes boetes;
    @Autowired
    CameraBerichten cameraBerichten;

    @RabbitListener(queues = "queue_cameraberichten" )
    public void ReadCameraCamera(final CameraBericht cameraBericht){
        try {
            log.info("Received message with default configuration: {}", cameraBericht.toString());
            cameraBerichten.register(boetes);
            cameraBerichten.addCameraBericht(cameraBericht);
            cameraBerichten.unregister(boetes);

        } catch (Exception e){
            e.printStackTrace();
        }
    }




}

