package be.kdg.simulator.model;

import be.kdg.simulator.Input.CameraMessageReceiver;
import be.kdg.simulator.Output.CameraMessageSender;
import org.springframework.stereotype.Component;

@Component
public class CameraBerichten  {
    private int delay;
    private CameraMessageReceiver cameraMessageReceiver;
    private CameraMessageSender cameraMessageSender;

    //getters and setters
    public int getDelay() {
        return delay;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }
    public CameraMessageReceiver getCameraInputModus() {
        return cameraMessageReceiver;
    }
    public void setCameraInputModus(CameraMessageReceiver cameraMessageReceiver) {
        this.cameraMessageReceiver = cameraMessageReceiver;
    }
    public CameraMessageSender getOutputModusCameraBerichten() {
        return cameraMessageSender;
    }
    public void setOutputModusCameraBerichten(CameraMessageSender cameraMessageSender) {
        this.cameraMessageSender = cameraMessageSender;
    }

    //Methods
    public void CreateCameraBerichten() throws InterruptedException {
        cameraMessageReceiver.CreateCameraBerichten(cameraMessageSender,delay);
    }



}
