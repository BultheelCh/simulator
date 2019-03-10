package be.kdg.processor.model;

import org.springframework.stereotype.Component;

@Component
public class Voertuig {
    private String plateId;
    private String nationalNumber;
    private String euroNumber;

    public String getPlateId() {
        return plateId;
    }
    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }
    public String getNationalNumber() {
        return nationalNumber;
    }
    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }
    public String getEuroNumber() {
        return euroNumber;
    }
    public void setEuroNumber(String euroNumber) {
        this.euroNumber = euroNumber;
    }


}
