package be.kdg.processor;

import be.kdg.sa.services.CameraServiceProxy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProxyCameraService implements CameraService{

    @Override
    public String get(int cameraId) {
        CameraServiceProxy  camSerProxy = new CameraServiceProxy();
        try {
            return camSerProxy.get(cameraId );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /*private void ConvertResultCameraService(String result){
       // JsonReader
        //Json g = new Json( );
        //Camera camera = fo g.fromJson(result, Camera.class);

        //test


    }*/

}
