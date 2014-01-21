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
package neocphelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import neocphelper.model.FindOrb;
import neocphelper.model.MPCweb;
import neocphelper.model.NEOCP;
import neocphelper.model.SkyXConnection;
import neocphelper.model.SkyXDB;

/**
 *
 * @author Albert
 */
public class NEOCPHelper extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Boolean useSkyx;
    private ObservableList<NEOCP> neocpData = FXCollections.observableArrayList();
    private final SkyXConnection skyxconn = new SkyXConnection();

    /**
     * Constructor
     */
    public NEOCPHelper() {
    }

    public void printFindOrb() {
        FindOrb foobj = new FindOrb();
        foobj.setNeocpData(neocpData);
        String output = foobj.genFindOrb();
        SaveFile(output, "Save FindOrb Observation File");
    }

    public void genSmallDB() {
        SkyXDB skyxobj = new SkyXDB();
        skyxobj.setNeocpData(neocpData);
        String output = skyxobj.genSmallDB();
        SaveFile(output, "Save TheSkyX Small Asteroid DB File");
    }

    public void genLargeDB() {
        SkyXDB skyxobj = new SkyXDB();
        skyxobj.setNeocpData(neocpData);
        String output = skyxobj.genLargeDB();
        SaveFile(output, "Save TheSkyX Large Asteroid DB File");
    }

    public void getNEOCP() {
        neocpData.clear();
        MPCweb mpc = new MPCweb();
        neocpData = mpc.getneocpData(neocpData);
    }

    public SkyXConnection getskyxconn() {
        return this.skyxconn;
    }

    public void updateNEOCP() throws IOException, ClassNotFoundException {
        for (NEOCP neocp : neocpData) {
            try {
                neocp.populateFromSkyX(skyxconn);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
                Logger.getLogger(NEOCPHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("NEOCPHelper");

        try {
            // Load the root layout from the fxml file
            FXMLLoader loader = new FXMLLoader(NEOCPHelper.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);

            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
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
            FXMLLoader loader = new FXMLLoader(NEOCPHelper.class
                    .getResource("view/NEOCPHelper.fxml"));
            AnchorPane overviewPage = (AnchorPane) loader.load();

            rootLayout.setCenter(overviewPage);

            // Give the controller access to the main app
            NEOCPHelperController controller = loader.getController();

            controller.setMainApp(
                    this);
        } catch (IOException e) {
        }
    }

    private void SaveFile(String content, String title) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File file = fileChooser.showSaveDialog(primaryStage);

        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();

        } catch (IOException ex) {
            Logger.getLogger(NEOCPHelper.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

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
