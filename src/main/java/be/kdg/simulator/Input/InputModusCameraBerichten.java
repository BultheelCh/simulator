package be.kdg.simulator.Input;

import be.kdg.simulator.Output.OutputModusCameraBerichten;
import be.kdg.simulator.model.CameraBericht;

public interface InputModusCameraBerichten {
    public  CameraBericht CreateCameraBericht();
    public void CreateCameraBerichten(OutputModusCameraBerichten outputModusCameraBerichten, int delay) throws InterruptedException;
}
