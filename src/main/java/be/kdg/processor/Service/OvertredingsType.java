package be.kdg.processor.Service;

import be.kdg.processor.model.Boete;
import be.kdg.processor.model.CameraBericht;

public interface OvertredingsType {
    public Boete berekeningBoete(CameraBericht cameraBericht);
}
