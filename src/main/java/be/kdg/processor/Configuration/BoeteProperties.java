package be.kdg.processor.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("boete")
public class BoeteProperties {

    private double emissieBoetefactor;
    private double snelheidBoetefactor;

    public double getEmissieBoetefactor() {
        return emissieBoetefactor;
    }
    public void setEmissieBoetefactor(double emissieBoetefactor) {
        this.emissieBoetefactor = emissieBoetefactor;
    }
    public double getSnelheidBoetefactor() {
        return snelheidBoetefactor;
    }
    public void setSnelheidBoetefactor(double snelheidBoetefactor) {
        this.snelheidBoetefactor = snelheidBoetefactor;
    }




}
