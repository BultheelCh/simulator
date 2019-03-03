package be.kdg.simulator.Input;

import be.kdg.simulator.OutputModusCameraBerichten;
import be.kdg.simulator.model.CameraBericht;
import be.kdg.simulator.InputModusCameraBerichten;

public class FileModus implements InputModusCameraBerichten {
    @Override
    public CameraBericht CreateCameraBericht() {
        return null;
    }

    @Override
    public void CreateCameraBerichten(OutputModusCameraBerichten outputModusCameraBerichten, int delay) throws InterruptedException {

    }





/*    public List<CameraBericht> ReadCameraBerichtenFromCSV(String filename, String splitter){
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
    }*/



}
