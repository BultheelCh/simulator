package be.kdg.processor.model;

import org.springframework.stereotype.Component;

@Component
public class Camera {
    private int cameraId;
    private Location location;
//    private float breedtegraad;
//    private float lengtegraad;
    private Segment segment;
    private String euroNorm;

    //getters and setters
    public int getCameraId() {
        return cameraId;
    }
    public void setCameraId(int cameraId) {
        this.cameraId = cameraId;
    }
/*
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
    */
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
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof Camera) {
            Camera camera = (Camera) obj;
            if (this.getCameraId() == ((Camera) obj).getCameraId()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "cameraId=" + cameraId +
                ", location=" + location +
                ", segment=" + segment +
                ", euroNorm='" + euroNorm + '\'' +
                '}';
    }
}
