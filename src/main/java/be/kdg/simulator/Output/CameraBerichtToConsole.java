package be.kdg.simulator.Output;

import be.kdg.simulator.model.CameraBericht;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CameraBerichtToConsole implements CameraMessageSender {
    private static final Logger log = LoggerFactory.getLogger(CameraBerichtToConsole.class);

    @Override
    public void Send(CameraBericht cameraBericht) {
        log.info(cameraBericht.toString());
    }
}
