package be.kdg.processor.model;


import be.kdg.processor.Service.OvertredingsType;
import org.springframework.stereotype.Component;

@Component
public abstract class Overtreding implements OvertredingsType {

    private Camera camera;
    private OvertredingsType overtredingsType;

    public Camera getCamera() {
        return camera;
    }
    public void setCamera(Camera camera) {
        this.camera = camera;
    }
    public OvertredingsType getOvertredingsType() {
        return overtredingsType;
    }
    public void setOvertredingsType(OvertredingsType overtredingsType) {
        this.overtredingsType = overtredingsType;
    }

    public abstract Boete berekeningBoete(CameraBericht cameraBericht) ;
}
