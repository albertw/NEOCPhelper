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

/**
 *
 * @author Albert White <albert.white@gmail.com>
 */
public class sky6ObjectInformation {

    // See http://www.bisque.com/scripttheskyx/classsky6_object_information.html
    private String sk6ObjInfoProp_CATALOG;           // 53
    private Double sk6ObjInfoProp_RA_NOW;            // 54
    private Double sk6ObjInfoProp_DEC_NOW;           // 55 
    private Double sk6ObjInfoProp_RA_2000;           // 56
    private Double sk6ObjInfoProp_DEC_2000;          // 57
    private Double sk6ObjInfoProp_AZM;               // 58
    private Double sk6ObjInfoProp_ALT;               // 59
    private Double sk6ObjInfoProp_RA_RATE_ASPERSEC;  // 77
    private Double sk6ObjInfoProp_DEC_RATE_ASPERSEC; // 78,

    public String getSk6ObjInfoProp_CATALOG() {
        return sk6ObjInfoProp_CATALOG;
    }

    public void setSk6ObjInfoProp_CATALOG(String sk6ObjInfoProp_CATALOG) {
        this.sk6ObjInfoProp_CATALOG = sk6ObjInfoProp_CATALOG;
    }

    public Double getSk6ObjInfoProp_RA_NOW() {
        return sk6ObjInfoProp_RA_NOW;
    }

    public void setSk6ObjInfoProp_RA_NOW(Double sk6ObjInfoProp_RA_NOW) {
        this.sk6ObjInfoProp_RA_NOW = sk6ObjInfoProp_RA_NOW;
    }

    public Double getSk6ObjInfoProp_DEC_NOW() {
        return sk6ObjInfoProp_DEC_NOW;
    }

    public void setSk6ObjInfoProp_DEC_NOW(Double sk6ObjInfoProp_DEC_NOW) {
        this.sk6ObjInfoProp_DEC_NOW = sk6ObjInfoProp_DEC_NOW;
    }

    public Double getSk6ObjInfoProp_RA_2000() {
        return sk6ObjInfoProp_RA_2000;
    }

    public void setSk6ObjInfoProp_RA_2000(Double sk6ObjInfoProp_RA_2000) {
        this.sk6ObjInfoProp_RA_2000 = sk6ObjInfoProp_RA_2000;
    }

    public Double getSk6ObjInfoProp_DEC_2000() {
        return sk6ObjInfoProp_DEC_2000;
    }

    public void setSk6ObjInfoProp_DEC_2000(Double sk6ObjInfoProp_DEC_2000) {
        this.sk6ObjInfoProp_DEC_2000 = sk6ObjInfoProp_DEC_2000;
    }

    public Double getSk6ObjInfoProp_AZM() {
        return sk6ObjInfoProp_AZM;
    }

    public void setSk6ObjInfoProp_AZM(Double sk6ObjInfoProp_AZM) {
        this.sk6ObjInfoProp_AZM = sk6ObjInfoProp_AZM;
    }

    public Double getSk6ObjInfoProp_ALT() {
        return sk6ObjInfoProp_ALT;
    }

    public void setSk6ObjInfoProp_ALT(Double sk6ObjInfoProp_ALT) {
        this.sk6ObjInfoProp_ALT = sk6ObjInfoProp_ALT;
    }

    public Double getSk6ObjInfoProp_RA_RATE_ASPERSEC() {
        return sk6ObjInfoProp_RA_RATE_ASPERSEC;
    }

    public void setSk6ObjInfoProp_RA_RATE_ASPERSEC(Double sk6ObjInfoProp_RA_RATE_ASPERSEC) {
        this.sk6ObjInfoProp_RA_RATE_ASPERSEC = sk6ObjInfoProp_RA_RATE_ASPERSEC;
    }

    public Double getSk6ObjInfoProp_DEC_RATE_ASPERSEC() {
        return sk6ObjInfoProp_DEC_RATE_ASPERSEC;
    }

    public void setSk6ObjInfoProp_DEC_RATE_ASPERSEC(Double sk6ObjInfoProp_DEC_RATE_ASPERSEC) {
        this.sk6ObjInfoProp_DEC_RATE_ASPERSEC = sk6ObjInfoProp_DEC_RATE_ASPERSEC;
    }              
}
