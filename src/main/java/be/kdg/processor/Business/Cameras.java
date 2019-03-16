package be.kdg.processor.Business;

import be.kdg.processor.Service.ProxyCameraService;
import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraBericht;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cameras {
    @Autowired
    private ProxyCameraService proxyCameraService;

    private List<Camera> cameraList = new ArrayList<>();

    public List<Camera> getCameraList() {
        return cameraList;
    }
    public void setCameraList(List<Camera> cameraList) {
        this.cameraList = cameraList;
    }

    public Camera SelectCamera(CameraBericht cameraBericht ){
        String cameraResult = proxyCameraService.get(cameraBericht.getId());
        Gson gson = new Gson();
        Camera camera = gson.fromJson(cameraResult, Camera.class);
        if (camera ==null){
            return null;
        }
        for(Camera c : cameraList){
            if (c.equals(camera)){
                return camera;
            }
        }

        cameraList.add(camera);

        return camera;
    }


}
