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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;

/**
 *
 * @author Albert White <albert.white@gmail.com>
 */
public class SkyXDB {

    private ObservableList<NEOCP> neocpData;
    private String smalldb;
    private String largedb;

    public void setNeocpData(ObservableList<NEOCP> neocpData) {
        this.neocpData = neocpData;
    }

    public String genSmallDB() {
        String nextLine;
        URL url;
        URLConnection urlConn;
        InputStreamReader inStream;
        BufferedReader buff;
        this.smalldb = "";
        for (Iterator<NEOCP> it = this.neocpData.iterator(); it.hasNext();) {
            NEOCP neocp = it.next();
            try {
                url = new URL(
                        "http://scully.cfa.harvard.edu/cgi-bin/showobsorbs.cgi?Obj="
                        + neocp.getTmpdesig() + "&orb=y");
                urlConn = url.openConnection();
                inStream = new InputStreamReader(
                        urlConn.getInputStream());
                buff = new BufferedReader(inStream);

                while (true) {
                    nextLine = buff.readLine();
                    if (nextLine != null) {
                        Pattern pattern = Pattern.compile("NEOCPNomin");
                        Matcher matcher = pattern.matcher(nextLine);
                        if (matcher.find()) {
                            //output = output + nextLine + (System.getProperty("line.separator"));;

                            Pattern splitPattern = Pattern.compile("^(.{7}) "
                                    + "(.{4})  (.{4})  (.{5}) (.{9})  (.{9})  "
                                    + "(.{9})  (.{9})  (.{9})  (.{10})  "
                                    + "(.{10})");

                            Matcher m = splitPattern.matcher(nextLine);
                            while (m.find()) {
                                String TmpDesig = m.group(1).trim();
                                Float H = Float.parseFloat(m.group(2).trim());
                                Float G = Float.parseFloat(m.group(3).trim());
                                String Epoch = m.group(4).trim();
                                Float M = Float.parseFloat(m.group(5).trim());
                                Float Peri = Float.parseFloat(m.group(6).trim());
                                Float Node = Float.parseFloat(m.group(7).trim());
                                Float Incl = Float.parseFloat(m.group(8).trim());
                                Float e = Float.parseFloat(m.group(9).trim());
                                Float n = Float.parseFloat(m.group(10).trim());
                                Float a = Float.parseFloat(m.group(11).trim());
                                this.smalldb = this.smalldb + String.format("  %-19.19s|%-14.14s|%8.6f  "
                                        + "|%8f|%8.4f|%8.4f |%8.4f| 2000|"
                                        + "%9.4f  |%5.2f|%-5.2f|   0.00" + (System.getProperty("line.separator")), TmpDesig,
                                        unpackEpoch(Epoch), e, a, Incl, Node, Peri, M,
                                        H, G);
                            }
                            break;  // We're only taking the first orbit.
                            // So no point continuing to parse.
                        }
                    } else {
                        break;
                    }
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(SkyXDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SkyXDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.smalldb;
    }

    public String genLargeDB() {
        String nextLine;
        URL url;
        URLConnection urlConn;
        InputStreamReader inStream;
        BufferedReader buff;
        this.largedb = "";

        try {
            for (NEOCP neocp : neocpData) {
                url = new URL(
                        "http://scully.cfa.harvard.edu/cgi-bin/showobsorbs.cgi?Obj="
                        + neocp.getTmpdesig() + "&orb=y");
                urlConn = url.openConnection();
                inStream = new InputStreamReader(
                        urlConn.getInputStream());
                buff = new BufferedReader(inStream);

                while (true) {
                    nextLine = buff.readLine();
                    if (nextLine != null) {
                        Pattern pattern = Pattern.compile("NEOCPNomin");
                        Matcher matcher = pattern.matcher(nextLine);
                        if (matcher.find()) {
                            //output = output + nextLine + (System.getProperty("line.separator"));;

                            Pattern splitPattern = Pattern.compile("^(.{7}) "
                                    + "(.{4})  (.{4})  (.{5}) (.{9})  (.{9})  "
                                    + "(.{9})  (.{9})  (.{9})  (.{10})  "
                                    + "(.{10})");

                            Matcher m = splitPattern.matcher(nextLine);
                            while (m.find()) {
                                String TmpDesig = m.group(1).trim();
                                Float H = Float.parseFloat(m.group(2).trim());
                                Float G = Float.parseFloat(m.group(3).trim());
                                String Epoch = m.group(4).trim();
                                Float M = Float.parseFloat(m.group(5).trim());
                                Float Peri = Float.parseFloat(m.group(6).trim());
                                Float Node = Float.parseFloat(m.group(7).trim());
                                Float Incl = Float.parseFloat(m.group(8).trim());
                                Float e = Float.parseFloat(m.group(9).trim());
                                Float n = Float.parseFloat(m.group(10).trim());
                                Float a = Float.parseFloat(m.group(11).trim());

                                this.largedb = this.largedb + String.format("%7s %5.2f %5.2f %5s "
                                        + "%9.5f  %9.5f  %9.5f  %9.5f  %9.7f %11.8f %11.7f"
                                        + "  0 NEOCP        10   1    1 days "
                                        + "0.00         Z72GUESS   0000    %-28s     "
                                        + (System.getProperty("line.separator")), TmpDesig,
                                        H, G, Epoch, M, Peri, Node, Incl,
                                        e, n, a, TmpDesig);
                            }
                            break;  // We're only taking the first orbit.
                            // So no point continuing to parse.
                        }
                    } else {
                        break;
                    }
                }
            }

        } catch (MalformedURLException e) {
            System.out.println("Please check the URL:"
                    + e.toString());
        } catch (IOException e1) {
            System.out.println("Can't read  from the Internet: "
                    + e1.toString());
        }
        return (this.largedb);
    }

    public String unpackEpoch(String packed) {
        String Year = "0000";
        String Month = "00";
        String Day = "00";
        Map<String, String> map = new HashMap<>();
        map.put("1", "01");
        map.put("2", "02");
        map.put("3", "03");
        map.put("4", "04");
        map.put("5", "05");
        map.put("6", "06");
        map.put("7", "07");
        map.put("8", "08");
        map.put("9", "09");
        map.put("A", "10");
        map.put("B", "11");
        map.put("C", "12");
        map.put("D", "13");
        map.put("E", "14");
        map.put("F", "15");
        map.put("G", "16");
        map.put("H", "17");
        map.put("I", "18");
        map.put("J", "19");
        map.put("K", "20");
        map.put("L", "21");
        map.put("M", "22");
        map.put("N", "23");
        map.put("O", "24");
        map.put("P", "25");
        map.put("Q", "26");
        map.put("R", "27");
        map.put("S", "28");
        map.put("T", "29");
        map.put("U", "30");
        map.put("V", "31");

        Pattern splitPattern = Pattern.compile("(.)(..)(.)(.)");
        Matcher m = splitPattern.matcher(packed);
        if (m.find()) {
            Year = (map.get(m.group(1).trim()) + m.group(2).trim());
            Month = (map.get(m.group(3).trim()));
            Day = (map.get(m.group(4).trim()));
        }
        return String.format("%s %s %s" + ".000", Year, Month, Day);
    }

}
