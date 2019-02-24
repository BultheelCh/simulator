package be.kdg.simulator;

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
    private int delay;

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
    public int getDelay() {
        return delay;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }

    //Constructors
    public CameraBericht(){};
    public CameraBericht(int id, Timestamp timestamp, String license) {
        this.id = id;
        this.timestamp = timestamp;
        this.license = license;
        this.delay = 0;
    }

    public CameraBericht(int id, String license, int delay) {
        this.id = id;
        this.license= license;
        this.delay = delay;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    //Methods
    @Override
    public String toString(){
        return String.format("Id: %5d  tijdstip: %s nummerplaat: %s", getId(),new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( getTimestamp()),getLicense() );

    }

}
