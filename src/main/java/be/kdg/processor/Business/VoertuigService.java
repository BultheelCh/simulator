package be.kdg.processor.Business;

import be.kdg.processor.Service.ProxyLicenseService;
import be.kdg.processor.model.Voertuig;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoertuigService {

    @Autowired
    private ProxyLicenseService proxyLicenseService;

    public Voertuig GetVoertuigInfo(String license){
        Voertuig voertuiginfo=null;
        String voertuigInfo = proxyLicenseService.get(license);
        Gson gson = new Gson();
        voertuiginfo = gson.fromJson(voertuigInfo, Voertuig.class);
        return voertuiginfo;
    }

}
