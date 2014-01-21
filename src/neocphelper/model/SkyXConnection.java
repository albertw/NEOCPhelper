/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neocphelper.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Albert
 */
public class SkyXConnection {

    private String host = "127.0.0.1";
    private Integer port = 3040;
    private Socket sock;
    private OutputStream os = null;
    private InputStream is = null;

    public SkyXConnection() {
        host = "127.0.0.1";
        port = 3040;
    }

    public String send(String command) throws IOException {

        int actualBuffered;
        char[] charArray = new char[1024];
        connect();

        PrintWriter out = new PrintWriter(this.os, true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.is));

        out.println("/* Java Script */ " + command);
        actualBuffered = in.read(charArray, 0, 1024);
        disconnect();
        String[] result = String.valueOf(charArray).split("\\|");
        return (result[0]);
    }

    public void testconnection() throws IOException {
        connect();
        disconnect();
    }

    public void connect() throws IOException {
        this.sock = new Socket(this.host, this.port);
        this.os = sock.getOutputStream();
        this.is = sock.getInputStream();
    }

    private void disconnect() throws IOException {
        this.sock.close();
    }

    public String sky6ObjectInformation(String Target) throws IOException {
        String command = "var Target = \"" + Target + "\"; "
                + "var Target56 = 0;"
                + "var Target57 = 0;"
                + "var Target58 = 0;"
                + "var Target59 = 0;"
                + "var Target77 = 0;"
                + "var Target78 = 0;"
                + "var Out = \"\";"
                + "var err;"
                + "sky6StarChart.LASTCOMERROR = 0;"
                + "sky6StarChart.Find(Target);"
                + "err = sky6StarChart.LASTCOMERROR;"
                + "if (err != 0) {"
                + "            Out = Target + \" not found.\"        "
                + "} else {"
                + "            sky6ObjectInformation.Property(56);"
                + "            Target56 = sky6ObjectInformation.ObjInfoPropOut;"
                + "            sky6ObjectInformation.Property(57);"
                + "            Target57 = sky6ObjectInformation.ObjInfoPropOut;"
                + "            sky6ObjectInformation.Property(58);"
                + "            Target58 = sky6ObjectInformation.ObjInfoPropOut;"
                + "            sky6ObjectInformation.Property(59);"
                + "            Target59 = sky6ObjectInformation.ObjInfoPropOut;"
                + "            sky6ObjectInformation.Property(77);"
                + "            Target77 = sky6ObjectInformation.ObjInfoPropOut;"
                + "            sky6ObjectInformation.Property(78);"
                + "            Target78 = sky6ObjectInformation.ObjInfoPropOut;"
                + "            Out = \"sk6ObjInfoProp_RA_2000:\"+String(Target56)+\"\\n"
                + "sk6ObjInfoProp_DEC_2000:\"+String(Target57)+\"\\n"
                + "sk6ObjInfoProp_AZM:\"+String(Target58)+\"\\n"
                + "sk6ObjInfoProp_ALT:\"+String(Target59)+\"\\n"
                + "sk6ObjInfoProp_RA_RATE_ASPERSEC:\"+String(Target77)+\"\\n"
                + "sk6ObjInfoProp_DEC_RATE_ASPERSEC:\"+String(Target78);"
                + "}";
        return (send(command));
    }
    
    public String convRAtoSex(String ra) {
        float raf = Float.parseFloat(ra);
        int rahour = (int) raf;
        float rafrac = raf - (int) raf;
        int ramin = (int) (60 * rafrac);
        float rasecfrac = (60 * rafrac - ramin) * 60;

        return rahour + "h " + ramin + "m " + String.format("%2.2f", rasecfrac) + "s";
    }

    public String convDectoSex(String ra) {
        float raf = Float.parseFloat(ra);
        int rasign = (int) Math.signum(raf);
        int radeg = (int) Math.abs(raf);
        raf = Math.abs(raf);
        float rafrac = raf - (int) raf;
        int ramin = (int) (60 * rafrac);
        float rasecfrac = (60 * rafrac - ramin) * 60;

        return radeg * rasign + "° " + ramin + "\' " + String.format("%2.2f", rasecfrac) + "\"";
    }
}
