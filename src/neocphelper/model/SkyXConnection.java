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

    public String GetRA2000(String Target) throws IOException {
        String command = "var Target = \"" + Target + "\"; "
                + "var TargetRA = 0;"
                + "var TargetDec = 0;"
                + "var Out = \"\";"
                + "var err;"
                + "sky6StarChart.LASTCOMERROR = 0;"
                + "sky6StarChart.Find(Target);"
                + "err = sky6StarChart.LASTCOMERROR;"
                + "if (err != 0) {"
                + "            Out = Target + \" not found.\"        "
                + "} else {"
                + "            sky6ObjectInformation.Property(56);"
                + "            TargetRA = sky6ObjectInformation.ObjInfoPropOut;"
                + "            Out = String(TargetRA); }";
        return (send(command));
    }

    public String GetDec2000(String Target) throws IOException {
        String command = "var Target = \"" + Target + "\"; "
                + "var TargetRA = 0;"
                + "var TargetDec = 0;"
                + "var Out = \"\";"
                + "var err;"
                + "sky6StarChart.LASTCOMERROR = 0;"
                + "sky6StarChart.Find(Target);"
                + "err = sky6StarChart.LASTCOMERROR;"
                + "if (err != 0) {"
                + "            Out = Target + \" not found.\"        "
                + "} else {"
                + "            sky6ObjectInformation.Property(57);"
                + "            TargetRA = sky6ObjectInformation.ObjInfoPropOut;"
                + "            Out = String(TargetRA); }";
      String result = send(command);
        Float x;
        try {
            Float.parseFloat(result);
            return (result);
        } catch (NumberFormatException r) {
            return (null);
        }
    }
    
    public String GetDecRate(String Target) throws IOException {
        String command = "var Target = \"" + Target + "\"; "
                + "var TargetRA = 0;"
                + "var TargetDec = 0;"
                + "var Out = \"\";"
                + "var err;"
                + "sky6StarChart.LASTCOMERROR = 0;"
                + "sky6StarChart.Find(Target);"
                + "err = sky6StarChart.LASTCOMERROR;"
                + "if (err != 0) {"
                + "            Out = Target + \" not found.\"        "
                + "} else {"
                + "            sky6ObjectInformation.Property(78);"
                + "            TargetRA = sky6ObjectInformation.ObjInfoPropOut;"
                + "            Out = String(TargetRA); }";
        String result = send(command);
        Float x;
        try {
            Float.parseFloat(result);
            return (result);
        } catch (NumberFormatException r) {
            return (null);
        }
    }

    public String GetRARate(String Target) throws IOException {
        String command = "var Target = \"" + Target + "\"; "
                + "var TargetRA = 0;"
                + "var TargetDec = 0;"
                + "var Out = \"\";"
                + "var err;"
                + "sky6StarChart.LASTCOMERROR = 0;"
                + "sky6StarChart.Find(Target);"
                + "err = sky6StarChart.LASTCOMERROR;"
                + "if (err != 0) {"
                + "            Out = Target + \" not found.\"        "
                + "} else {"
                + "            sky6ObjectInformation.Property(77);"
                + "            TargetRA = sky6ObjectInformation.ObjInfoPropOut;"
                + "            Out = String(TargetRA); }";
        String result = send(command);
        Float x;
        try {
            Float.parseFloat(result);
            return (result);
        } catch (NumberFormatException r) {
            return (null);
        }
    }

    public String getAlt(String Target) throws IOException {
        String command = "var Target = \"" + Target + "\"; "
                + "var TargetRA = 0;var TargetDec = 0;        var Out = \"\";"
                + "var err;"
                + "sky6StarChart.LASTCOMERROR = 0;"
                + "sky6StarChart.Find(Target);"
                + "err = sky6StarChart.LASTCOMERROR;"
                + "if (err != 0) {"
                + "            Out = Target + \" not found.\"        "
                + "} else {"
                + "            sky6ObjectInformation.Property(59);"
                + "            TargetDec = sky6ObjectInformation.ObjInfoPropOut;"
                + "            Out = String(TargetDec);        }";
        //System.out.println(command);
        String result = send(command);
        Float x;
        try {
            Float.parseFloat(result);
            return (result);
        } catch (NumberFormatException r) {
            return (null);
        }
    }

    public String getAz(String Target) throws IOException {
        String command = "var Target = \"" + Target + "\"; "
                + "var TargetRA = 0;var TargetDec = 0;        var Out = \"\";"
                + "var err;"
                + "sky6StarChart.LASTCOMERROR = 0;"
                + "sky6StarChart.Find(Target);"
                + "err = sky6StarChart.LASTCOMERROR;"
                + "if (err != 0) {"
                + "            Out = Target + \" not found.\"        "
                + "} else {"
                + "            sky6ObjectInformation.Property(58);"
                + "            TargetDec = sky6ObjectInformation.ObjInfoPropOut;"
                + "            Out = String(TargetDec);        }";
        String result = send(command);
        try {
            Float.parseFloat(result);
            return (result);
        } catch (NumberFormatException r) {
            return (null);
        }
    }

    public String TargetCenter(String Target) throws IOException {
        String command = "var err; var Target = \"" + Target + "\"; "
                + "sky6StarChart.LASTCOMERROR = 0;"
                + "sky6StarChart.Find(Target); "
                + "err = sky6StarChart.LASTCOMERROR;"
                + "if (err != 0) {"
                + "            Out = Target + \" not found.\"        "
                + "} else {"
                + "            TheSkyXAction.execute(\"TARGET_CENTER\");"
                + "}";
        return (send(command));
    }

    public String SlewToTarget(String Target) {
        return "Not Implemented";
    }

    public String SlewToRADec2000(String RA, String Dec) throws IOException {
        String command = "var TargetRA = \"" + RA + "\";"
                + "var TargetDec = \"" + Dec + "\";"
                + "var Out;"
                + "sky6RASCOMTele.Connect();"
                + "if (sky6RASCOMTele.IsConnected==0)/*Connect failed for some reason*/{"
                + "     Out = \"Not connected\""
                + "}else{"
                + "     sky6RASCOMTele.Asynchronous = true;"
                + "     sky6RASCOMTele.SlewToRaDec(TargetRA, TargetDec,\"\");"
                + "     Out  = \"OK\";}";
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

    public Double getPA(String target) throws IOException{
        /* Basically :
        Sin(A)/Sin(a)=Sin(C)/sin(c)
        */
        Double ra = Double.parseDouble(GetRA2000(target));
        Double rarate = Double.parseDouble(GetRARate(target));
        Double dec = Double.parseDouble(GetDec2000(target));
        Double decrate = Double.parseDouble(GetDecRate(target));
        Double rate = getTotalRate(target)/60;
        
        Double a = Math.toRadians(90-(dec+decrate));
        Double C = Math.toRadians(rarate);
        Double c = Math.toRadians(rate);
       System.out.println(target +" a:"+a+" c:" +c + " C:" +C);
        return(Math.toDegrees(Math.asin(
                Math.sin(C)*Math.sin(a)/Math.sin(c))));
    }
    
    public Double getTotalRate(String target) throws IOException {
        /*
        Based on 'Practical Astronomy with your calculator' (Duffet-Smith 1988) 
        p51, but it's just a cosine rule of spherical trig. Simply 
        sqrt(ra^2+dec^2) wont work, you need spherical trig not planar!
        */
        Double rarateR = Math.toRadians(Double.parseDouble(GetRARate(target)));
        Double decrate = Double.parseDouble(GetDecRate(target));
        Double dec = Double.parseDouble(GetDec2000(target));
        Double decR = Math.toRadians(Double.parseDouble(GetDec2000(target)));
        Double dec2R = Math.toRadians(dec+decrate);
        Double result = Math.toDegrees(Math.acos(
                Math.sin(decR)*Math.sin(dec2R)+
                Math.cos(decR)*Math.cos(dec2R)*Math.cos(rarateR)));         
        
        return (result*60);
    }

    public String SetChartFOV(String FOV) {
        return ("");
    }

    public String GetChartFOV(String FOV) {
        return ("");
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

}
