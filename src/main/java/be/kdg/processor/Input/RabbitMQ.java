package be.kdg.processor.Input;


import be.kdg.processor.Business.CameraBerichten;
import be.kdg.processor.Repsitory.BoeteRepository;
import be.kdg.processor.model.Boete;
import be.kdg.processor.model.CameraBericht;
import be.kdg.processor.model.SnelheidsOvertreding;
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
    CameraBerichten cameraBerichten;
    @Autowired
    SnelheidsOvertreding snelheidsOvertreding;
    @Autowired
    BoeteRepository boeteRepository;


    @RabbitListener(queues = "queue_cameraberichten" )
    public void ReadCameraCamera(final CameraBericht cameraBericht){
        try {
            log.info("Received message with default configuration: {}", cameraBericht.toString());
            cameraBerichten.addCameraBericht(cameraBericht);
            Boete boete = cameraBericht.processCameraBericht(snelheidsOvertreding);
            if (boete != null) {
                boeteRepository.save(boete);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }




}

