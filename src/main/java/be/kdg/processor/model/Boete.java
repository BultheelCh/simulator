package be.kdg.processor.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "boete", schema = "CB")
public class Boete {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="BoeteId")
    private int id;
    //@Column(name="nationaalNr",length=50, nullable = false)
    private String nationaalNr;
    private double bedrag;
    private String  overtredingsType;
    private String nummerplaat;
    private LocalDate datum;

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
    public LocalDate getDatum() {
        return datum;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    //gebruikt voor aanmaken database
    protected Boete(){}

    public Boete(String nationaalNr, String nummerplaat, double bedrag, String overtredingsType, LocalDate datum){
        this.bedrag= bedrag;
        this.nationaalNr= nationaalNr;
        this.nummerplaat = nummerplaat;
        this.overtredingsType = overtredingsType;
        this.datum = datum;
    }

    @Override
    public String toString(){
        return String.format("NationaalNr:%s  Nummerplaat:%s  Overtredingstype:%s  boetebedrag:%f", nationaalNr, nummerplaat, overtredingsType, bedrag);
    }


}
