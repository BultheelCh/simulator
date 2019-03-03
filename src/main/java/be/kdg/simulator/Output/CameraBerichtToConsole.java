package be.kdg.simulator.Output;

import be.kdg.simulator.OutputModusCameraBerichten;
import be.kdg.simulator.model.CameraBericht;

public class CameraBerichtToConsole implements OutputModusCameraBerichten {
    @Override
    public void SendTo(CameraBericht cameraBericht) {
        System.out.println(cameraBericht.toString());
    }
}