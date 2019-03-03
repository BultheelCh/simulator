package be.kdg.processor.model;

public class Camera {
    private int cameraId;
    private float breedtegraad;
    private float lengtegraad;
    private WegSegment wegsegment;

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
    public WegSegment getWegsegment() {
        return wegsegment;
    }
    public void setWegsegment(WegSegment wegsegment) {
        this.wegsegment = wegsegment;
    }


}
