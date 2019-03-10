package be.kdg.processor.Business;

import be.kdg.processor.model.CameraBericht;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public  class CameraBerichten {

    private List<CameraBericht> cameraBerichten = new ArrayList<>();

    public List<CameraBericht> getCameraBerichten() {
        return cameraBerichten;
    }
    public void addCameraBericht(CameraBericht cameraBericht){
        this.cameraBerichten.add(cameraBericht);
    }


}
