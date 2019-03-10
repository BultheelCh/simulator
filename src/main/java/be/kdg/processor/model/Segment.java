package be.kdg.processor.model;

import org.springframework.stereotype.Component;

@Component
public class Segment {
    private int  connectedCameraId;
    private double distance;
    private double speedLimit;

    public int getConnectedCamera() {
        return connectedCameraId;
    }
    public void setConnectedCamera(int connectedCameraId) {
        this.connectedCameraId = connectedCameraId;
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
