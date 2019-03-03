package be.kdg.processor.model;

public class WegSegment {
    private int  connectedCamera;
    private double distance;
    private double speedLimit;

    public int getConnectedCamera() {
        return connectedCamera;
    }
    public void setConnectedCamera(int connectedCamera) {
        this.connectedCamera = connectedCamera;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public double getSpeedLimit() {
        return speedLimit;
    }
    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = speedLimit;
    }
}
