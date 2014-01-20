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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private sky6ObjectInformation skyxdata = new sky6ObjectInformation();

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

    public void populateFromSkyX(SkyXConnection skyxconn) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        String lskyxdata = skyxconn.sky6ObjectInformation(this.tmpdesig);
        if (!lskyxdata.contains("not found")) {
            //Object skyxdataobj = Class.forName("complete.classpath.and.sky6ObjectInformation").newInstance();

            String lines[] = lskyxdata.split("\\r?\\n");
            for (String line : lines) {
                Pattern patt = Pattern.compile("^(\\w+):([\\d\\.\\-]+)");
                Matcher match = patt.matcher(line);
                while (match.find()) {
                    String vari = match.group(1);
                    String capitol = Character.toString(vari.charAt(0)).toUpperCase();
                    vari = capitol + vari.substring(1);
                    Method meth = skyxdata.getClass().getDeclaredMethod("set" + vari, Double.class);
                    meth.invoke(skyxdata, Double.parseDouble(match.group(2)));
                }
            }
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

    public Double getPa() throws IOException {
        /* Basically :
         Sin(A)/Sin(a)=Sin(C)/sin(c)
         */
        if (this.skyxdata.getSk6ObjInfoProp_RA_RATE_ASPERSEC() != null) {

            Double rarate = this.skyxdata.getSk6ObjInfoProp_RA_RATE_ASPERSEC();
            Double dec1 = this.skyxdata.getSk6ObjInfoProp_DEC_2000();
            Double decrate = this.skyxdata.getSk6ObjInfoProp_DEC_RATE_ASPERSEC();
            Double rate = getRate() / 60;

            Double aa = Math.toRadians(90 - (dec1 + decrate));
            Double C = Math.toRadians(rarate);
            Double c = Math.toRadians(rate);
            Double ans = (Math.toDegrees(Math.asin(
                    Math.sin(C) * Math.sin(aa) / Math.sin(c))));
            if (ans < 0) {
                ans = 360 + ans;
            }
            return (Math.round((ans) * 100.0) / 100.0);

        } else {
            return null;
        }
    }

    public Double getRate() throws IOException {
        /*
         Based on 'Practical Astronomy with your calculator' (Duffet-Smith 1988) 
         p51, but it's just a cosine rule of spherical trig. Simply 
         sqrt(ra^2+dec^2) wont work, you need spherical trig not planar!
         */

        if (this.skyxdata.getSk6ObjInfoProp_RA_RATE_ASPERSEC() != null) {
            Double rarateR = Math.toRadians(this.skyxdata.getSk6ObjInfoProp_RA_RATE_ASPERSEC());
            Double decrate = this.skyxdata.getSk6ObjInfoProp_DEC_RATE_ASPERSEC();
            Double dec1 = this.skyxdata.getSk6ObjInfoProp_DEC_2000();
            Double dec1R = Math.toRadians(dec1);
            Double dec2R = Math.toRadians(dec1 + decrate);
            Double result = Math.toDegrees(Math.acos(
                    Math.sin(dec1R) * Math.sin(dec2R)
                    + Math.cos(dec1R) * Math.cos(dec2R) * Math.cos(rarateR)));
            return (Math.round((result * 60) * 100.0) / 100.0);

        } else {
            return null;
        }
    }

    public Double getAlt() {
        if (this.skyxdata.getSk6ObjInfoProp_ALT() != null) {
            return (Math.round(this.skyxdata.getSk6ObjInfoProp_ALT() * 100.0) / 100.0);
        } else {
            return (this.skyxdata.getSk6ObjInfoProp_ALT());
        }
    }

    public Double getAz() {
        if (this.skyxdata.getSk6ObjInfoProp_AZM() != null) {
            return (Math.round(this.skyxdata.getSk6ObjInfoProp_AZM() * 100.0) / 100.0);
        } else {
            return (this.skyxdata.getSk6ObjInfoProp_AZM());
        }
    }

    @Override
    public String toString() {
        return this.tmpdesig;
    }

}
