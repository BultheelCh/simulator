package be.kdg.processor.Business;

import be.kdg.processor.Configuration.BoeteProperties;
import be.kdg.processor.model.Boete;
import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraBericht;
import be.kdg.processor.model.Voertuig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmissieBoete extends Boete {
    private static final Logger log = LoggerFactory.getLogger(EmissieBoete.class);

    @Autowired
    Cameras cameras;
    @Autowired
    VoertuigService voertuigService;
    @Autowired
    BoeteProperties boeteProperties;


    private int diffEuroNorm;

    @Override
    public Boete berekeningBoete(CameraBericht cameraBericht) {

        try {
            //get EmmissieCamera
            Camera camera = cameras.SelectCamera(cameraBericht);

            if (camera.getEuroNorm() == "") {
                return null;
            }

            Voertuig voertuig = voertuigService.GetVoertuigInfo(cameraBericht.getLicense());
            log.info("Voertuig: " + voertuig.toString());
            log.info("Emissiecamera: "+ camera.toString());

            diffEuroNorm =  Integer.parseInt(camera.getEuroNorm())-Integer.parseInt(voertuig.getEuroNumber());
            if (diffEuroNorm<=0){
                //voertuig is toegelaten
                return null;
            }

            //check gebruiker heeft meermaals boete


            return createEmissieBoete(voertuig, cameraBericht);

        }
        catch (Exception ex){
            log.error(ex.getMessage());
        }
        return null;
    }

    private Boete createEmissieBoete(Voertuig voertuig, CameraBericht cameraBericht){
        return new Boete(voertuig.getNationalNumber(),
                         voertuig.getPlateId(),
                         berekenBoeteBedrag(),
                         this.getClass().getSimpleName(),
                         cameraBericht.getTimestamp().toLocalDateTime().toLocalDate());
    }

    private double berekenBoeteBedrag(){
        if (diffEuroNorm>0){
            return boeteProperties.getEmissieBoetefactor() * diffEuroNorm;
        }
        return 0;
    }
}
