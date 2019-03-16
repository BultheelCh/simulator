package be.kdg.processor.Business;

import be.kdg.processor.Configuration.BoeteProperties;
import be.kdg.processor.Service.ProxyCameraService;
import be.kdg.processor.Service.ProxyLicenseService;
import be.kdg.processor.model.Boete;
import be.kdg.processor.model.Camera;
import be.kdg.processor.model.CameraBericht;
import be.kdg.processor.model.Voertuig;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ComponentScan("be.kdg.processor.Service")
public class SnelheidsBoete extends Boete {
    private static final Logger log = LoggerFactory.getLogger(SnelheidsBoete.class);

    @Autowired
    CameraBerichtService cameraBerichtService;
    @Autowired
    Cameras cameras;
    @Autowired
    BoeteProperties boeteProperties;
    @Autowired
    private ProxyCameraService proxyCameraService;
    @Autowired
    private ProxyLicenseService proxyLicenseService;


    @Override
    public Boete berekeningBoete(CameraBericht cameraBericht ) {
        Camera connectedCamera;
        Boete boete=null;
        List<CameraBericht> cameraBerichtList = new ArrayList<>();

        //Bepaling 2e camerabericht
        try{
            Camera camera = getCamera(cameraBericht);
            if (camera.getSegment()== null || camera.getSegment().getConnectedCameraId()==0){
            //if (camera.getSegment()== null || camera.getSegment().getConnectedCameraId()=){
                //Camerabericht 2e camera => geen extra info
                 connectedCamera = cameras.getCameraList()
                                            .stream()
                                            .filter(c -> camera.getCameraId() == c.getSegment().getConnectedCameraId() )
                                            .findAny()
                                            .orElse(null);

                 if (connectedCamera != null) {
                     cameraBerichtList = cameraBerichtService.getCameraBerichten()
                             .stream()
                             .filter(b -> b.getId() == connectedCamera.getCameraId())
                             .filter(b -> b.getLicense().equals(cameraBericht.getLicense()))
                             .filter(b -> b.getTimestamp().before(cameraBericht.getTimestamp()))
                             .collect(Collectors.toList());
                 }

            } else {
                connectedCamera = camera;
                cameraBerichtList = cameraBerichtService.getCameraBerichten()
                        .stream()
                        .filter(c -> c.getId() == connectedCamera.getSegment().getConnectedCameraId())
                        .filter(c -> c.getLicense()==cameraBericht.getLicense())
                        .filter(c ->  c.getTimestamp().after( cameraBericht.getTimestamp()))
                        .sorted()
                        .collect(Collectors.toList());
            }
            if (cameraBerichtList.size()==0){
                return null;
            }

            //Aanmaken nieuwe boete
            boete = createNieuweBoete(cameraBericht, cameraBerichtList.get(0), connectedCamera);

            //verwijderd verwerkte berichten
            if(boete!=null) {
                cameraBerichtService.getCameraBerichten().remove(cameraBericht);
                cameraBerichtService.getCameraBerichten().remove(cameraBerichtList.get(0));
            }
        } catch (Exception ex ) {
            log.warn(ex.getMessage());
            //System.out.println(ex.getMessage());
        }
        return boete;
    }
    private Boete createNieuweBoete(CameraBericht camerabericht1, CameraBericht cameraBericht2, Camera connectedCamera){
        Voertuig voertuigInfo =  getVoertuigInfo(camerabericht1.getLicense());
        if (voertuigInfo==null){
            return null;
        }

        long tijdsverschil =  (camerabericht1.getTimestamp().getTime() - cameraBericht2.getTimestamp().getTime())/1000;

        double gemSnelheid = berekenSnelheid(connectedCamera.getSegment().getDistance(),tijdsverschil);

        double boetebedrag =  berekenBedrag(gemSnelheid,connectedCamera.getSegment().getSpeedLimit(),boeteProperties.getSnelheidBoetefactor() );

        if (boetebedrag <= 0){
            return null;
        }

        return new Boete(voertuigInfo.getNationalNumber(), voertuigInfo.getPlateId(),  boetebedrag, this.getClass().getSimpleName(),camerabericht1.getTimestamp().toLocalDateTime().toLocalDate());
    }
    private Camera getCamera(CameraBericht cameraBericht ){
        String cameraResult = proxyCameraService.get(cameraBericht.getId());
        Gson gson = new Gson();
        Camera camera = gson.fromJson(cameraResult, Camera.class);
        if (camera ==null){
            return null;
        }
        for(Camera c : cameras.getCameraList()){
            if (c.equals(camera)){
                return camera;
            }
        }

        cameras.getCameraList().add(camera);

        return camera;
    }
    private double berekenSnelheid(double afstand, long seconden){
        //log.info(String.valueOf(((afstand/seconden)*3600/1000)));
        return ((afstand/seconden)*3600/1000);
    }
    private double berekenBedrag(double gemSnelheid, double maxSnelheid, double boetefactor){
        if (gemSnelheid < maxSnelheid){
            return 0;
        }
        return (gemSnelheid-maxSnelheid)*boetefactor;
    }
    private Voertuig getVoertuigInfo(String license){
        Voertuig voertuiginfo=null;
        String voertuigInfo = proxyLicenseService.get(license);
        Gson gson = new Gson();
        voertuiginfo = gson.fromJson(voertuigInfo, Voertuig.class);
        return voertuiginfo;
    }

}
