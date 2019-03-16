package be.kdg.processor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Component
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
    public CameraBericht(){

    };
    public CameraBericht(@JsonProperty("id") final int id,
                         @JsonProperty("timestamp") Timestamp timestamp,
                         @JsonProperty("license")String license) {
        this.id = id;
        this.timestamp = timestamp;
        this.license = license;
    }


    @Override
    public String toString(){
        return String.format("Id: %5d  tijdstip: %s nummerplaat: %s", getId(),new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format( getTimestamp()),getLicense() );

    }

}
