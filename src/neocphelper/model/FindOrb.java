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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Albert White <albert.white@gmail.com>
 */
public class FindOrb {

    private ObservableList<NEOCP> neocpData;
    private String observations;

    public void setNeocpData(ObservableList<NEOCP> neocpData) {
        this.neocpData = neocpData;
    }

    public String genFindOrb() {
        String nextLine;
        URL url;
        URLConnection urlConn;
        InputStreamReader inStream;
        BufferedReader buff;
        this.observations = "";
        try {
            for (NEOCP neocp : neocpData) {
                url = new URL(
                        "http://scully.cfa.harvard.edu/cgi-bin/showobsorbs.cgi?Obj="
                        + neocp.getTmpdesig() + "&obs=y");
                urlConn = url.openConnection();
                inStream = new InputStreamReader(
                        urlConn.getInputStream());
                buff = new BufferedReader(inStream);

                while (true) {
                    nextLine = buff.readLine();
                    if (nextLine != null) {
                        Pattern pattern = Pattern.compile("html");
                        Matcher matcher = pattern.matcher(nextLine);
                        if (matcher.find()) {
                        } else {
                            this.observations = this.observations + nextLine
                                    + (System.getProperty("line.separator"));
                        }
                    } else {
                        break;
                    }
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("Please check the URL:" + e.toString());
        } catch (IOException e1) {
            Label err = new Label("Can't read  from the Internet: "
                    + e1.toString());

            err.setWrapText(true);

            Stage stage = new Stage();
            StackPane layout = new StackPane();
            layout.getChildren().setAll(err);
            stage.setTitle("ERROR");
            stage.setScene(new Scene(layout));
            stage.show();
        }
        return (this.observations);
    }
}
