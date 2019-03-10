package be.kdg.processor.model;

import org.springframework.stereotype.Component;

@Component
public class Camera {
    private int cameraId;
    private float breedtegraad;
    private float lengtegraad;
    private Segment segment;
    private String euroNorm;

    //getters and setters
    public int getCameraId() {
        return cameraId;
    }
    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }
    public float getBreedtegraad() {
        return breedtegraad;
    }
    public void setBreedtegraad(float breedtegraad) {
        this.breedtegraad = breedtegraad;
    }
    public float getLengtegraad() {
        return lengtegraad;
    }
    public void setLengtegraad(float lengtegraad) {
        this.lengtegraad = lengtegraad;
    }
    public Segment getSegment() {
        return segment;
    }
    public void setSegment(Segment segment) {
        this.segment = segment;
    }
    public String getEuroNorm() {
        return euroNorm;
    }
    public void setEuroNorm(String euroNorm) {
        this.euroNorm = euroNorm;
    }
}
