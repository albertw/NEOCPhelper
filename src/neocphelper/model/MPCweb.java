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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;

/**
 *
 * @author Albert White <albert.white@gmail.com>
 */
public class MPCweb {

    public ObservableList getneocpData(ObservableList<NEOCP> neocpData) {
        String nextLine;
        String failedLines = "";
        URL url;
        URLConnection urlConn;
        InputStreamReader inStream;
        BufferedReader buff;
        try {
            // Create the URL obect that points
            // at the default file index.html
            url = new URL("http://www.minorplanetcenter.net/iau/NEO/neocp.txt");
            urlConn = url.openConnection();
            inStream = new InputStreamReader(
                    urlConn.getInputStream());
            buff = new BufferedReader(inStream);

            // Read and print the lines from index.html
            while (true) {
                nextLine = buff.readLine();
                if (nextLine != null) {
                    Pattern splitPattern = Pattern.compile("^(.{7}) (.{3}) "
                            + "(.{12}) (.{8}) (.{8}) (.{4}) (.{21})  (.{7}) "
                            + "(.{3})  (.{5}) (.{4})");
                    Matcher m = splitPattern.matcher(nextLine);
                    if (m.find()) {

                        int m2 = Integer.parseInt(m.group(2).trim());
                        Float m4 = Float.parseFloat(m.group(4).trim());
                        Float m6 = Float.parseFloat(m.group(6).trim());
                        int m9 = Integer.parseInt(m.group(9).trim());
                        Float m10 = Float.parseFloat(m.group(10).trim());
                        Float m11 = Float.parseFloat(m.group(11).trim());
                        neocpData.add(new NEOCP(m.group(1).trim(),
                                m2, m.group(3).trim(),
                                m4, m.group(5).trim(),
                                m6, m.group(7).trim(),
                                m.group(8).trim(), m9,
                                m10, m11));
                    } else {
                        failedLines = failedLines + nextLine + System.getProperty("line.separator");
                    }

                } else {
                    break;
                }
            }
            if (!"".equals(failedLines)) {
                System.out.println("The following lines did not parse correctly:" + failedLines);
            }
        } catch (MalformedURLException e) {
            System.out.println("Please check the URL:"
                    + e.toString());
        } catch (IOException e1) {
            System.out.println("Can't read  from the Internet: "
                    + e1.toString());
        }
        return neocpData;
    }
}
