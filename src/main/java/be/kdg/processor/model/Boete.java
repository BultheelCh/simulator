package be.kdg.processor.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class Boete {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    //@Column(name="ID")
    private int id;
    //@Column(name="nationaalNr",length=50, nullable = false)
    private String nationaalNr;
    private double bedrag;
    private String  overtredingsType;
    private String nummerplaat;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNationaalNr() {
        return nationaalNr;
    }
    public void setNationaalNr(String nationaalNr) {
        this.nationaalNr = nationaalNr;
    }
    public double getBedrag() {
        return bedrag;
    }
    public void setBedrag(double bedrag) {
        this.bedrag = bedrag;
    }
    public String getOvertredingsType() {
        return overtredingsType;
    }
    public void setOvertredingsType(String overtredingsType) {
        this.overtredingsType = overtredingsType;
    }
    public String getNummerplaats() {
        return nummerplaat;
    }
    public void setNummerplaats(String nummerplaats) {
        this.nummerplaat = nummerplaat;
    }

    protected Boete(){}

    public Boete(String nationaalNr, String nummerplaat, double bedrag, String overtredingsType){
        this.bedrag= bedrag;
        this.nationaalNr= nationaalNr;
        this.nummerplaat = nummerplaat;
        this.overtredingsType = overtredingsType;
    }

    @Override
    public String toString(){
        return String.format("NationaalNr:%s  Nummerplaat:%s  Overtredingstype:%s  boetebedrag:%f", nationaalNr, nummerplaat, overtredingsType, bedrag);
    }


}
