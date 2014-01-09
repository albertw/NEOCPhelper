/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neocphelperfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
        neocpData.add(new NEOCP("Ceres","","","","","","","","","",""));
        neocpData.add(new NEOCP("Vesta","","","","","","","","","",""));
        neocpData.add(new NEOCP("2001MP14","","","","","","","","","",""));
        neocpData.add(new NEOCP("foo","","","","","","","","","",""));
    }

    public void clearNEOCP() {
        neocpData.clear();

        String nextLine;
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

                    Pattern splitPattern = Pattern.compile("^(.{7}) (.{3}) "
                            + "(.{12}) (.{8}) (.{8}) (.{4}) (.{20})  (.{9}) "
                            + "(.{3})  (.{4}) (.{4})");
                    Matcher m = splitPattern.matcher(nextLine);
                    List<String> stringList = new ArrayList<String>();
                    while (m.find()) {
                        stringList.add(m.group(1).trim());

                        neocpData.add(new NEOCP(m.group(1).trim(),
                                m.group(2).trim(),m.group(3).trim(),
                                m.group(4).trim(),m.group(5).trim(),
                                m.group(6).trim(),m.group(7).trim(),
                                m.group(8).trim(),m.group(9).trim(),
                                m.group(10).trim(),m.group(11).trim()));

                        System.out.println(m.group(1).trim());
                        System.out.println(m.group(2).trim());
                    }

                } else {
                    break;
                }
            }
        } catch (MalformedURLException e) {
            System.out.println("Please check the URL:"
                    + e.toString());
        } catch (IOException e1) {
            System.out.println("Can't read  from the Internet: "
                    + e1.toString());
        }

        System.out.println(neocpData);
    }

    /**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */
    public ObservableList<NEOCP> getNEOCPData() {
        System.out.println(neocpData);
        return neocpData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("NEOCPHelperFX");

        try {
            // Load the root layout from the fxml file
            FXMLLoader loader = new FXMLLoader(NEOCPHelperFX.class.getResource("RootLayout.fxml"));
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
    public void showNEOCPOverview() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(NEOCPHelperFX.class.getResource("NEOCPHelperfx.fxml"));
            AnchorPane overviewPage = (AnchorPane) loader.load();
            rootLayout.setCenter(overviewPage);

            // Give the controller access to the main app
            NEOCPHelperfxController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
}
