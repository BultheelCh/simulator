package be.kdg.processor.Service;

import be.kdg.sa.services.LicensePlateServiceProxy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProxyLicenseService {

    public String get(String license){
        LicensePlateServiceProxy licensePlateServiceProxy = new LicensePlateServiceProxy();
        try{
            return licensePlateServiceProxy.get(license);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
