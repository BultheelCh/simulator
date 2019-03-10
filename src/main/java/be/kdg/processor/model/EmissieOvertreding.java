package be.kdg.processor.model;

import be.kdg.processor.Configuration.BoeteProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmissieOvertreding extends Overtreding {
    @Autowired
    BoeteProperties boeteProperties;

    @Override
    public Boete berekeningBoete(CameraBericht cameraBericht) {
        return null;
    }
}
