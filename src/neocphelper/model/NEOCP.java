/* 
 * Copyright (C) 2014 Albert White <albert.white@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package neocphelper.model;

import java.io.IOException;

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
    private Integer alt;
    private Integer az;
    private Float v;
    private String updated;
    private String note;
    private Integer observations;
    private Float arc;
    private Float h;

    private Float skyxra;
    private String skyxdec;
    private Double rarate;
    private Double decrate;
    private Double totalrate;
    private Double pa;
    
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
        this.discovery = discovery;
        this.ra = ra;
        this.dec = dec;
        this.v = v;
        this.updated = updated;
        this.note = note;
        this.observations = observations;
        this.arc = arc;
        this.h = h;

    }

    public void setaltaz(SkyXConnection skyxconn) throws IOException {
        //SkyXConnection skyxcon = new SkyXConnection();
        try {
            this.alt = (int) Float.parseFloat(skyxconn.getAlt(this.tmpdesig));
            this.az = (int) Float.parseFloat(skyxconn.getAz(this.tmpdesig));
            System.out.println(this.tmpdesig + " "
                    + skyxconn.getTotalRate(this.tmpdesig)
                    + " " + skyxconn.getPA(this.tmpdesig));

        } catch (NumberFormatException | NullPointerException f) {
            // Just means we didnt get a value back from TheSkyX
        }
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

    public Integer getAlt() {
        return alt;
    }

    public void setAlt(Integer alt) {
        this.alt = alt;
    }

    public Integer getAz() {
        return az;
    }

    public void setAz(Integer az) {
        this.az = az;
    }

    @Override
    public String toString() {
        return this.tmpdesig;
    }

}
