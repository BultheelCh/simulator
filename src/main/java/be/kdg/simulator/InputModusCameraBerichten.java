package be.kdg.simulator;

import be.kdg.simulator.model.CameraBericht;

public interface InputModusCameraBerichten {
    public  CameraBericht CreateCameraBericht();
    public void CreateCameraBerichten(OutputModusCameraBerichten outputModusCameraBerichten, int delay) throws InterruptedException;
}
