package be.kdg.processor.Service;

import be.kdg.processor.model.CameraBericht;

public interface Observer {

    public void berekenBoete(CameraBericht cameraBericht , OvertredingsType overtredingsType);

}
