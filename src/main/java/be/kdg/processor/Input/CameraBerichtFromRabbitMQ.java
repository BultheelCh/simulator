package be.kdg.processor.Input;


import be.kdg.processor.Business.Boetes;
import be.kdg.processor.Business.CameraBerichtService;
import be.kdg.processor.model.CameraBericht;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("be.kdg.processor.Service")
public class CameraBerichtFromRabbitMQ implements CameraMessageReceiver {

    private static final Logger log = LoggerFactory.getLogger(CameraBerichtFromRabbitMQ.class);

    @Autowired
    Boetes boetes;
    @Autowired
    CameraBerichtService cameraBerichtService;

    @RabbitListener(queues = "queue_cameraberichten" )
    public void ReadCameraBericht(final CameraBericht cameraBericht){
        try {
            log.info("Received message with default configuration: {}", cameraBericht.toString());
            cameraBerichtService.register(boetes);
            cameraBerichtService.addCameraBericht(cameraBericht);
            cameraBerichtService.unregister(boetes);

        } catch (Exception e){
            log.error(e.getMessage());

        }
    }
}

