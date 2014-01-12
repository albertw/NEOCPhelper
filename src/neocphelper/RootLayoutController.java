/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neocphelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Albert
 */
public class RootLayoutController {

    @FXML
    private MenuItem about;

    public void newAboutFired(ActionEvent event) {

        Text about = new Text("NEOCPHelper by Albert.white@gmail.com (Z72). 12-01-2014"
                + System.getProperty("line.separator") + "Loads the NEOCP list from the MPS and provides observations for Find_ORB and also asteroid databases for TheSkyX."
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "Very incomplete list of known bugs:"
                + System.getProperty("line.separator")
                + "1. We include objects on the NEOCP even if they have been moved to the PCCP. Resulting orbits may be plotted incorrectly."
                + System.getProperty("line.separator")
                + "2. There is no error checking for anything. Expect Java stack traces and silly orbits."
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "https://github.com/albertw/NEOCPhelper");
about.setWrappingWidth(400);
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("About NEOCPHelper");
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(about).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();
    }

    // Reference to the main application
    private NEOCPHelper nEOCPHelper;
}
