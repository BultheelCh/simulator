package be.kdg.simulator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

@Component
//@Scope(value="prototype") //standaard wordt er gewerkt met pattern sinlgeton.
// wanneer je meerdere instanties nodig hebt gebruik je pattern prototype.
public class BerichtenGenerator {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    private  final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private  final char[] ALPHANUMERIC = LETTERS.toCharArray();

    @Autowired //zoekt op type
   // @Qualifier("camber1") //zoekt op naam
    private CameraBericht cameraBericht;

    @Autowired
    private Producer producer;

    @Autowired
    private IOCameraBerichten ioCameraBerichten;

    //Constructor

    //Methods
    public void CreateBerichtenByModus(Modus modus, int maxId  , String fileName, String splitter, boolean onConsole) {
        switch(modus){
            case Random:
                try {
                    for (int i = 0; i < 1000; i++) {
                        producer.SendMessage(CreateRandomCameraBericht(maxId),onConsole);
                        Thread.sleep(500);
                    }
                    break;
                } catch (Exception ex){
                    ex.getMessage();
                }

            case File:
                List<CameraBericht> berichtenlijst = ioCameraBerichten.ReadCameraBerichtenFromCSV(fileName, splitter);
                for(CameraBericht bericht: berichtenlijst){
                    producer.SendMessage(bericht, onConsole);
                }
                break;
            case ToExport:
                ioCameraBerichten.WriteCameraBerichtenToCsv(fileName,splitter, 50,maxId,2000 ) ;
                break;
            default:
                break;
        }
    }

    public  CameraBericht CreateRandomCameraBericht(int maxId){
       try {

           //CameraBericht cameraBericht = new CameraBericht();
           cameraBericht.setId(CreateCameraID(maxId));
           cameraBericht.setTimestamp(new Timestamp(System.currentTimeMillis()));
           cameraBericht.setLicense(CreateNummerplaat());
           return cameraBericht;

       }
       catch (Exception ex){
           System.out.println(ex.getMessage());
       }

       return null;
    }

    public CameraBericht CreateCameraBericht(String[] velden){
         Integer cameraId = Integer.parseInt(velden[0]);
         String nummerplaat = velden[1];
         Integer delay = Integer.parseInt(velden[2]);

         return new CameraBericht(cameraId, nummerplaat, delay);
    }

    private   String CreateNummerplaat(){
        //first number
        Random nr1 = new Random();
        int firstNumber = nr1.nextInt(9)+1;


        //3 alphanumeric char
        StringBuilder alpha = new StringBuilder();
        for (int i=0; i<3; i++){
            alpha.append(ALPHANUMERIC[new Random().nextInt(ALPHANUMERIC.length)]);
        }

        //3 numeric char
        StringBuilder numeric = new StringBuilder();
        for (int j=0; j<3;j++){
            numeric.append(new Random().nextInt(10));
        }

        return String.format("%s-%s-%s", firstNumber, alpha.toString().toUpperCase(), numeric);
    }

    private  int CreateCameraID(int maxId){
        Random rn = new Random();
        return rn.nextInt(maxId)+1;
    }



}
