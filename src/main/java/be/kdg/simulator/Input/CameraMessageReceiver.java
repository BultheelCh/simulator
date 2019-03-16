package be.kdg.simulator.Input;

import be.kdg.simulator.Output.CameraMessageSender;
import be.kdg.simulator.model.CameraBericht;

public interface CameraMessageReceiver {
    public  CameraBericht CreateCameraBericht();
    public void CreateCameraBerichten(CameraMessageSender cameraMessageSender, int delay) throws InterruptedException;
}
