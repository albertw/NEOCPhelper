/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neocphelper.model;

/**
 *
 * @author Albert
 */
public class NEOCP {

    private String tmpdesig;
    private Integer score;
    private String discovery;
    private Float ra;
    private String dec;
    private Float v;
    private String updated;
    private String note;
    private Integer observations;
    private Float arc;
    private Float h;
    
    private Float g;
    private String epoch;
    private Float m;
    private Float peri;
    private Float node;
    private Float incl;
    private Float e;
    private Float n;
    private Float a;
    
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
    public NEOCP(String tmpdesig, Integer score, String discovery, Float ra, 
            String dec, Float v, String updated, String note, Integer observations,
            Float arc, Float h) {
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

    public Float getG() {
        return g;
    }

    public String getEpoch() {
        return epoch;
    }

    public Float getM() {
        return m;
    }

    public Float getPeri() {
        return peri;
    }

    public Float getNode() {
        return node;
    }

    public Float getIncl() {
        return incl;
    }

    public Float getE() {
        return e;
    }

    public Float getN() {
        return n;
    }

    public Float getA() {
        return a;
    }

    public Integer getScore() {
        return score;
    }

    public String getDiscovery() {
        return discovery;
    }

    public Float getRa() {
        return ra;
    }

    public String getDec() {
        return dec;
    }

    public Float getV() {
        return v;
    }

    public String getUpdated() {
        return updated;
    }

    public String getNote() {
        return note;
    }

    public Integer getObservations() {
        return observations;
    }

    public Float getArc() {
        return arc;
    }

    public Float getH() {
        return h;
    }
    

    @Override
    public String toString() {
        return this.tmpdesig;
    }

}
