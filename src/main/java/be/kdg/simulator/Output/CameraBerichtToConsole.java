package be.kdg.simulator.Output;

import be.kdg.simulator.model.CameraBericht;
import org.springframework.stereotype.Component;

@Component
public class CameraBerichtToConsole implements OutputModusCameraBerichten {
    @Override
    public void SendTo(CameraBericht cameraBericht) {
        System.out.println(cameraBericht.toString());
    }
}
