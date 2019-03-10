package be.kdg.processor.model;

import be.kdg.processor.Business.CameraBerichten;
import be.kdg.processor.Business.Cameras;
import be.kdg.processor.Configuration.BoeteProperties;
import be.kdg.processor.Service.ProxyCameraService;
import be.kdg.processor.Service.ProxyLicenseService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ComponentScan("be.kdg.processor.Service")
public class SnelheidsOvertreding extends Overtreding {
    @Autowired
    CameraBerichten cameraBerichten;
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
            if (camera.getSegment()== null || camera.getSegment().getConnectedCamera()==0){
                //Camerabericht 2e camera => geen extra info
                 connectedCamera = cameras.getCameraList()
                                            .stream()
                                            .filter(c -> camera.getCameraId() == c.getSegment().getConnectedCamera())
                                            .findAny()
                                            .orElse(null);

                 cameraBerichtList = cameraBerichten.getCameraBerichten()
                                                            .stream()
                                                            .filter(b -> (b.getId() == connectedCamera.getCameraId()))
                                                            .filter(b1 -> b1.getLicense().equals(cameraBericht.getLicense()))
                                                            .filter(b2 -> b2.getTimestamp().before(cameraBericht.getTimestamp()))
                                                            .collect(Collectors.toList());

            } else {
                connectedCamera = camera;
                cameraBerichtList = cameraBerichten.getCameraBerichten()
                        .stream()
                        .filter(c -> c.getId() == connectedCamera.getSegment().getConnectedCamera() &&
                                     c.getLicense()==cameraBericht.getLicense() &&
                                     c.getTimestamp().after( cameraBericht.getTimestamp()))
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
                cameraBerichten.getCameraBerichten().remove(cameraBericht);
                cameraBerichten.getCameraBerichten().remove(cameraBerichtList.get(0));
            }
        } catch (Exception ex ) {
            System.out.println(ex.getMessage());
        }
        return boete;
    }

    private Boete createNieuweBoete(CameraBericht camerabericht1, CameraBericht cameraBericht2, Camera connectedCamera){
        Voertuig voertuigInfo =  getVoertuigInfon(camerabericht1.getLicense());
        if (voertuigInfo==null){
            return null;
        }

        long tijdsverschil =  (camerabericht1.getTimestamp().getTime() - cameraBericht2.getTimestamp().getTime())/1000;

        double gemSnelheid = berekenSnelheid(connectedCamera.getSegment().getDistance(),tijdsverschil);

        double boetebedrag =  berekenBedrag(gemSnelheid,connectedCamera.getSegment().getSpeedLimit(),boeteProperties.getSnelheidBoetefactor() );

        if (boetebedrag <= 0){
            return null;
        }

        return new Boete(voertuigInfo.getNationalNumber(), voertuigInfo.getPlateId(),  boetebedrag, this.getClass().getSimpleName());
    }

    private Camera getCamera(CameraBericht cameraBericht ){
        String cameraResult = proxyCameraService.get(cameraBericht.getId());
        Gson gson = new Gson();
        Camera camera = gson.fromJson(cameraResult, Camera.class);
        if (camera ==null){
            return null;
        }
        cameras.getCameraList().add(camera);
        return camera;
    }

    private double berekenSnelheid(double afstand, long seconden){
        //System.out.println(((afstand/seconden)*3600/1000));
        return ((afstand/seconden)*3600/1000);
    }

    private double berekenBedrag(double gemSnelheid, double maxSnelheid, double boetefactor){
        if (gemSnelheid < maxSnelheid){
            return 0;
        }
        return (gemSnelheid-maxSnelheid)*boetefactor;
    }

    private Voertuig getVoertuigInfon(String license){
        Voertuig voertuiginfo=null;
        String voertuigInfo = proxyLicenseService.get(license);
        Gson gson = new Gson();
        voertuiginfo = gson.fromJson(voertuigInfo, Voertuig.class);
        return voertuiginfo;
    }

}
