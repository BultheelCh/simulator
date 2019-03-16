package be.kdg.simulator.Output;

import be.kdg.simulator.model.CameraBericht;

public interface CameraMessageSender {
    public void Send(CameraBericht cameraBericht);
}
