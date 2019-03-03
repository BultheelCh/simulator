package be.kdg.simulator.model;

import be.kdg.simulator.InputModusCameraBerichten;
import be.kdg.simulator.OutputModusCameraBerichten;
import org.springframework.stereotype.Component;

@Component
public class CameraBerichten  {
    private int delay;
    private InputModusCameraBerichten cameraInputModus;
    private OutputModusCameraBerichten  outputModusCameraBerichten;

    //getters and setters
    public int getDelay() {
        return delay;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }
    public InputModusCameraBerichten getCameraInputModus() {
        return cameraInputModus;
    }
    public void setCameraInputModus(InputModusCameraBerichten cameraInputModus) {
        this.cameraInputModus = cameraInputModus;
    }
    public OutputModusCameraBerichten getOutputModusCameraBerichten() {
        return outputModusCameraBerichten;
    }
    public void setOutputModusCameraBerichten(OutputModusCameraBerichten outputModusCameraBerichten) {
        this.outputModusCameraBerichten = outputModusCameraBerichten;
    }

    //Methods
    public void CreateCameraBerichten() throws InterruptedException {
        cameraInputModus.CreateCameraBerichten(outputModusCameraBerichten,delay);
    }



}
