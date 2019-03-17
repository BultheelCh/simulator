package be.kdg.processor.Business;

import be.kdg.processor.Repsitory.BoeteRepository;
import be.kdg.processor.model.Boete;
import be.kdg.processor.model.BoeteType;
import be.kdg.processor.model.CameraBericht;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
    public class Boetes implements Observer {

    private static final Logger log = LoggerFactory.getLogger(Boetes.class);

    @Autowired
    BoeteRepository boeteRepository;

    @Override
    public void berekenBoete(CameraBericht cameraBericht, BoeteType boeteType) {
        Boete boete = boeteType.berekeningBoete(cameraBericht);
        if (boete !=null){
            List<Boete> boetelijst = boeteRepository.findByNationaalNr(boete.getNationaalNr());
            if (boetelijst.size() ==0) {
                log.info("Berekende "+ boeteType.getClass().getSimpleName() + ": " + boete.toString());
                boeteRepository.save(boete);
            }
        }
    }
}
