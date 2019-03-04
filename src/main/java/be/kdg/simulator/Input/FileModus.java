package be.kdg.simulator.Input;

import be.kdg.simulator.AppProperties;
import be.kdg.simulator.Output.OutputModusCameraBerichten;
import be.kdg.simulator.model.CameraBericht;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;


@Component
public class FileModus implements InputModusCameraBerichten {
    private static final Logger log = LoggerFactory.getLogger(FileModus.class);

    @Autowired
    AppProperties appProperties;

    @Override
    public CameraBericht CreateCameraBericht() {
        return null;
    }

    @Override
    public void CreateCameraBerichten(OutputModusCameraBerichten outputModusCameraBerichten, int delay) throws InterruptedException {
        try{
            //Path pathToFile = Paths.get(importfile);
            System.out.println(appProperties.getImportfilename());
           // System.out.println(env.getProperty("app.importfilename") );
            //Path pathToFile = Paths.get(env.getProperty("app.importfilename"));
            Path pathToFile = Paths.get(appProperties.getImportfilename());
            if (!Files.exists(pathToFile)){
                throw new FileNotFoundException("Path naar bestand bestaat niet!");
            }

            try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8))
            {
                //lees de eerste lijn van het bestand
                String line = br.readLine();

                //stop met inlezen wanneer alle lijnen ingelezen zijn.
                while(line != null){
                    String[] berichtVelden = line.split(",");

                    int id =Integer.parseInt( berichtVelden[0]);
                    delay = Integer.valueOf( berichtVelden[2]);
                    String license = berichtVelden[1];

                    CameraBericht bericht = new CameraBericht(id,   new Timestamp(System.currentTimeMillis()) ,license);
                    bericht.setCameraOutputModus(outputModusCameraBerichten);
                    TimeUnit.SECONDS.sleep(delay/1000);
                    bericht.SendCameraBericht();

                    //lees de volgende lijn
                    line = br.readLine();
                }

            } catch (IOException IO){
                log.error(IO.getMessage());
                IO.printStackTrace();
            }

        }
        catch (FileNotFoundException ef){
            log.error(ef.getMessage());
            ef.printStackTrace();
        }
    }







}
