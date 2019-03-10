package be.kdg.processor.Business;

import be.kdg.processor.model.Camera;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Cameras {
    private List<Camera> cameraList = new ArrayList<>();

    public List<Camera> getCameraList() {
        return cameraList;
    }
    public void setCameraList(List<Camera> cameraList) {
        this.cameraList = cameraList;
    }
}
