package be.kdg.processor.model;

import be.kdg.processor.Service.OvertredingsType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Component
//@Component("camber1")
public class CameraBericht implements Serializable{
    private int id;
    private Timestamp timestamp;
    private String license;


    //getter and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public String getLicense() {
        return license;
    }
    public void setLicense(String license) {
        this.license = license;
    }


    //Constructors
    public CameraBericht(){};
    public CameraBericht(@JsonProperty("id") final int id,
                         @JsonProperty("timestamp") Timestamp timestamp,
                         @JsonProperty("license")String license) {
        this.id = id;
        this.timestamp = timestamp;
        this.license = license;
    }

    //Methods
    public Boete processCameraBericht(OvertredingsType overtredingsType){
        //Bepaal overtreding
        Boete b = overtredingsType.berekeningBoete(this);
        if (b !=null){
            System.out.println("Berekende snelheisovertreding: " + b.toString());
        }
        return b;

    }

    @Override
    public String toString(){
        return String.format("Id: %5d  tijdstip: %s nummerplaat: %s", getId(),new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( getTimestamp()),getLicense() );

    }

}







/*package be.kdg.processor.model;


import be.kdg.processor.Input.InputModus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Component
//@Component("camber1")
public class CameraBericht implements Serializable {
    private int id;
    private Timestamp timestamp;
    private String license;

    private InputModus cameraInputModus;

    //getter and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    public String getLicense() {
        return license;
    }
    public void setLicense(String license) {
        this.license = license;
    }
    public void setCameraInputModus(InputModus cameraInputModus) {
        this.cameraInputModus = cameraInputModus;
    }

    //Constructors
    public CameraBericht(){};
    public CameraBericht(int id, Timestamp timestamp, String license) {
        this.id = id;
        this.timestamp = timestamp;
        this.license = license;
    }
    public CameraBericht(int id, String license, int delay) {
        this.id = id;
        this.license= license;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    //Methods
    @Override
    public String toString(){
        return String.format("Id: %5d  tijdstip: %s nummerplaat: %s", getId(),new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( getTimestamp()),getLicense() );

    }
    @RabbitListener(queues="queue_cameraberichten")
    //@RabbitHandler
    public void ReadCameraBericht(String cameraBericht) {
        System.out.println(String.format("Ontvangen bericht op queue({}): {}", "queue_cameraberichten",  cameraBericht.toString()));
    }
*//*    public void ReadCameraBericht(){
        cameraInputModus.ReadCameraBericht();
    }*//*

}*/

