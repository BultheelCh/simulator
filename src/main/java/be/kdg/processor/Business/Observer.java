package be.kdg.processor.Business;

import be.kdg.processor.model.BoeteType;
import be.kdg.processor.model.CameraBericht;

public interface Observer {

    public void berekenBoete(CameraBericht cameraBericht , BoeteType boetetype
    );

}
