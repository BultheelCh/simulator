package be.kdg.simulator.Input;

import be.kdg.simulator.Configuration.AppProperties;
import be.kdg.simulator.Output.CameraMessageSender;
import be.kdg.simulator.model.CameraBericht;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@ComponentScan("be.kdg.simulator.Service")
public class RandomModus implements CameraMessageReceiver {

    private  final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private  final char[] ALPHANUMERIC = LETTERS.toCharArray();
    private AppProperties appProperties;

    @Override
    public  CameraBericht CreateCameraBericht() {
            CameraBericht cameraBericht = new CameraBericht(CreateCameraID(appProperties.getMaxCameraId()),new Timestamp(System.currentTimeMillis()),CreateNummerplaat());
            return cameraBericht;
        }

    @Override
    public void CreateCameraBerichten(CameraMessageSender cameraMessageSender, int delay) throws InterruptedException {
        //oneindige lus
        for(int i=0;;i++){
            CameraBericht cameraBericht = CreateCameraBericht();
            //CameraBericht cameraBericht = new CameraBericht(CreateCameraID(appProperties.getMaxCameraId()),new Timestamp(System.currentTimeMillis()),CreateNummerplaat());
            cameraBericht.setCameraOutputModus(cameraMessageSender);
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
        Random rn = new Random(maxId);
        return rn.nextInt(maxId)+1;
    }


}
