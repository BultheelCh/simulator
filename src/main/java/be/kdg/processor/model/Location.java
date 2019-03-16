package be.kdg.processor.model;

import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

@Component
public class Location {
    @SerializedName("lat")
    private double breedtegraad;
    @SerializedName("long")
    private double lengtegraad;

    public double getBreedtegraad() {
        return breedtegraad;
    }
    public void setBreedtegraad(double breedtegraad) {
        this.breedtegraad = breedtegraad;
    }
    public double getLengtegraad() {
        return lengtegraad;
    }
    public void setLengtegraad(double lengtegraad) {
        this.lengtegraad = lengtegraad;
    }

    @Override
    public String toString() {
        return "Location{" +
                "breedtegraad=" + breedtegraad +
                ", lengtegraad=" + lengtegraad +
                '}';
    }
}
