/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neocphelperfx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import neocphelperfx.model.NEOCP;

/**
 *
 * @author Albert
 */
public class NEOCPHelperFX extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<NEOCP> neocpData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public NEOCPHelperFX() {
        // Add some sample data
        /*
         neocpData.add(new NEOCP("Ceres","","","","","","","","","",""));
         neocpData.add(new NEOCP("Vesta","","","","","","","","","",""));
         neocpData.add(new NEOCP("2001MP14","","","","","","","","","",""));
         neocpData.add(new NEOCP("foo","","","","","","","","","",""));
         */
    }

    public void printFindOrb() {
        String nextLine;
        URL url = null;
        URLConnection urlConn = null;
        InputStreamReader inStream = null;
        BufferedReader buff = null;
        String output = "";
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
                            continue;
                        } else {
                            output = output + nextLine + (System.getProperty("line.separator"));
                        }
                    } else {
                        break;
                    }
                }
            }
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            fileChooser.setTitle("Save FindOrb Observation File");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                SaveFile(output, file);

            }
        } catch (MalformedURLException e) {
            System.out.println("Please check the URL:"
                    + e.toString());
        } catch (IOException e1) {
            System.out.println("Can't read  from the Internet: "
                    + e1.toString());
        }
    }

    public void genSmallDB() {
        String nextLine;
        URL url = null;
        URLConnection urlConn = null;
        InputStreamReader inStream = null;
        BufferedReader buff = null;
        String output = "";
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
                                output = output + String.format("  %-19.19s|%-14.14s|%8.6f  "
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
            }

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            fileChooser.setTitle("Save TheSkyX Small Asteroid DB File");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                SaveFile(output, file);

            }

        } catch (MalformedURLException e) {
            System.out.println("Please check the URL:"
                    + e.toString());
        } catch (IOException e1) {
            System.out.println("Can't read  from the Internet: "
                    + e1.toString());
        }

    }

    public void genLargeDB() {
        String nextLine;
        URL url = null;
        URLConnection urlConn = null;
        InputStreamReader inStream = null;
        BufferedReader buff = null;
        String output = "";
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

                                output = output + String.format("%7s %5.2f %5.2f %5s "
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

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            fileChooser.setTitle("Save TheSkyX Small Asteroid DB File");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                SaveFile(output, file);

            }

        } catch (MalformedURLException e) {
            System.out.println("Please check the URL:"
                    + e.toString());
        } catch (IOException e1) {
            System.out.println("Can't read  from the Internet: "
                    + e1.toString());
        }

    }

    public void getNEOCP() {

        neocpData.clear();

        String nextLine;
        String failedLines = "";
        URL url = null;
        URLConnection urlConn = null;
        InputStreamReader inStream = null;
        BufferedReader buff = null;
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
                    System.out.println(nextLine);
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
            if (failedLines != "") {
                Debug("The following lines did not parse correctly:" + failedLines);
            }
        } catch (MalformedURLException e) {
            System.out.println("Please check the URL:"
                    + e.toString());
            Debug("Please check the URL:"
                    + e.toString());
        } catch (IOException e1) {
            System.out.println("Can't read  from the Internet: "
                    + e1.toString());
            Debug("Can't read  from the Internet: "
                    + e1.toString());
        }
    }

    /**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */
    public ObservableList<NEOCP> getNEOCPData() {
        return neocpData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("NEOCPHelper");

        try {
            // Load the root layout from the fxml file
            FXMLLoader loader = new FXMLLoader(NEOCPHelperFX.class
                    .getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }

        showNEOCPOverview();
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Shows the person overview scene.
     */
    public
            void showNEOCPOverview() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(NEOCPHelperFX.class
                    .getResource("NEOCPHelperfx.fxml"));
            AnchorPane overviewPage = (AnchorPane) loader.load();

            rootLayout.setCenter(overviewPage);

            // Give the controller access to the main app
            NEOCPHelperfxController controller = loader.getController();

            controller.setMainApp(
                    this);
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }

    private void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(NEOCPHelperFX.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String unpackEpoch(String packed) {
        String Year = "0000";
        String Month = "00";
        String Day = "00";
        Map<String, String> map = new HashMap<String, String>();
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

    public void Debug(String msg) {

        Text about = new Text(msg);
        about.setWrappingWidth(400);
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Debug");
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(about).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();
    }
}
