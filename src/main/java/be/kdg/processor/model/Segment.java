package be.kdg.processor.model;

import org.springframework.stereotype.Component;

@Component
public class Segment {
    private int  connectedCameraId;
    //private Camera connectedCamera;
    private double distance;
    private double speedLimit;

    public int getConnectedCameraId() {
        return connectedCameraId;
    }
    public void setConnectedCameraId(int connectedCameraId) {
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
/*
    public Camera getConnectedCamera() {
        return connectedCamera;
    }
    public void setConnectedCamera(Camera connectedCamera) {
        this.connectedCamera = connectedCamera;
    }
    */

    @Override
    public String toString() {
        return "Segment{" +
                "connectedCameraId=" + connectedCameraId +
                ", distance=" + distance +
                ", speedLimit=" + speedLimit +
                '}';
    }
}
