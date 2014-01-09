/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neocphelperfx.model;

/**
 *
 * @author Albert
 */
public class NEOCP {

    private String tmpdesig;
    private String score;
    private String discovery;
    private String ra;
    private String dec;
    private String v;
    private String updated;
    private String note;
    private String observations;
    private String arc;
    private String h;
    
    /**
     * Default constructor.
     */
    public NEOCP() {
    }

    /**
     * Constructor with some initial data.
     *
     * @param tmpdesig
     * @param score
     * @param discovery
     * @param ra
     * @param dec
     * @param v
     * @param updated
     * @param note
     * @param observations
     * @param arc
     * @param h
     */
    public NEOCP(String tmpdesig, String score, String discovery, String ra, 
            String dec, String v, String updated, String note, String observations,
            String arc, String h) {
        this.tmpdesig = tmpdesig;
        this.score = score;
        this.discovery=discovery;
        this.ra=ra;
        this.dec=dec;
        this.v=v;
        this.updated=updated;
        this.note=note;
        this.observations=observations;
        this.arc=arc;
        this.h=h;
               
    }

    public String getTmpdesig() {
        return tmpdesig;
    }

    public String getScore() {
        return score;
    }

    public String getDiscovery() {
        return discovery;
    }

    public String getRa() {
        return ra;
    }

    public String getDec() {
        return dec;
    }

    public String getV() {
        return v;
    }

    public String getUpdated() {
        return updated;
    }

    public String getNote() {
        return note;
    }

    public String getObservations() {
        return observations;
    }

    public String getArc() {
        return arc;
    }

    public String getH() {
        return h;
    }
    

    @Override
    public String toString() {
        return this.tmpdesig;
    }

}
