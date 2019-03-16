package be.kdg.simulator.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class AppProperties {

    private String importfilename;
    private int delay;
    private int maxCameraId;

    public String getImportfilename() {
        return importfilename;
    }
    public void setImportfilename(String importfilename) {
        this.importfilename = importfilename;
    }
    public int getDelay() {
        return delay;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }
    public int getMaxCameraId() {
        return maxCameraId;
    }
    public void setMaxCameraId(int maxCameraId) {
        this.maxCameraId = maxCameraId;
    }
}
