package be.kdg.processor.Business;

import be.kdg.processor.Input.RabbitMQ;
import be.kdg.processor.Repsitory.BoeteRepository;
import be.kdg.processor.Service.Observer;
import be.kdg.processor.Service.OvertredingsType;
import be.kdg.processor.model.Boete;
import be.kdg.processor.model.CameraBericht;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
    public class Boetes implements Observer {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQ.class);

    @Autowired
    BoeteRepository boeteRepository;

    /*    @Override
    public void berekenBoete(OvertredingsType overtredingsType) {
//        Boete boete = processCameraBericht(overtredingsType);
//        if (boete != null) {
//            boeteRepository.save(boete);
//        }
    }

    public Boete processCameraBericht(CameraBericht cameraBericht, OvertredingsType overtredingsType){

    }*/

    @Override
    public void berekenBoete(CameraBericht cameraBericht, OvertredingsType overtredingsType) {
        //Bepaal overtreding
        Boete boete = overtredingsType.berekeningBoete(cameraBericht);
        if (boete !=null){
            log.info("Berekende snelheisovertreding: " + boete.toString());
            boeteRepository.save(boete);
        }
    }
}
