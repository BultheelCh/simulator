package be.kdg.simulator.Input;

import be.kdg.simulator.InputModusCameraBerichten;
import be.kdg.simulator.OutputModusCameraBerichten;
import be.kdg.simulator.model.CameraBericht;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Configuration
public class RandomModus implements InputModusCameraBerichten {

    private  final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private  final char[] ALPHANUMERIC = LETTERS.toCharArray();

    @Override
    public  CameraBericht CreateCameraBericht() {
            //instelbaar max voor cameraId => config file
            int maxId=100;
            CameraBericht cameraBericht = new CameraBericht(CreateCameraID(maxId),new Timestamp(System.currentTimeMillis()),CreateNummerplaat());
//            cameraBericht.setId(CreateCameraID(maxId));
//            cameraBericht.setTimestamp(new Timestamp(System.currentTimeMillis()));
//            cameraBericht.setLicense(CreateNummerplaat());
            return cameraBericht;
        }

    @Override
    public void CreateCameraBerichten(OutputModusCameraBerichten outputModusCameraBerichten, int delay) throws InterruptedException {
        int maxId=100;
        //oneindige lus
        for(int i=0;;i++){
            CameraBericht cameraBericht = new CameraBericht(CreateCameraID(maxId),new Timestamp(System.currentTimeMillis()),CreateNummerplaat());
            cameraBericht.setCameraOutputModus(outputModusCameraBerichten);
            TimeUnit.SECONDS.sleep(delay);
            cameraBericht.SendCameraBericht();
        }
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
