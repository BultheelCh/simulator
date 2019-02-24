package be.kdg.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class IOCameraBerichten {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private BerichtenGenerator berichtenGenerator;

    public List<CameraBericht> ReadCameraBerichtenFromCSV(String filename, String splitter){
        List<CameraBericht> cameraBerichten = new ArrayList<>();

        try{
            Path pathToFile = Paths.get(filename);
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

                    CameraBericht bericht = berichtenGenerator.CreateCameraBericht(berichtVelden);

                    //Toevoegen aan de lijst
                    cameraBerichten.add(bericht);

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

        return cameraBerichten;
    }

    public void WriteCameraBerichtenToCsv (String fileName, String splitter, int aantal, int maxId, int maxDelay) {

        checkFileExistsOrCreate(fileName);

        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));



            for(int i = 0; i<aantal;i++){
                CameraBericht cb = berichtenGenerator.CreateRandomCameraBericht(maxId);
                StringBuffer csvLine = new StringBuffer();
                csvLine.append(cb.getId() );
                csvLine.append(splitter);
                csvLine.append(cb.getLicense());
                csvLine.append(splitter);
                csvLine.append(new Random( ).nextInt(maxDelay));
                bw.write(csvLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch(UnsupportedEncodingException e) {
            e.getMessage();
        }
        catch(FileNotFoundException e ){
            e.getMessage();
        }
        catch(IOException e){
            e.getMessage();
        }

    }

    private void checkFileExistsOrCreate(String fileName){
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                //create file
                f.createNewFile();
            }
        }
        catch (IOException e){
            e.getMessage();
        }

    }


}
