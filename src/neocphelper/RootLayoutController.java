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

        Text abouttxt = new Text("NEOCPHelper by Albert.white@gmail.com (Z72). 20-01-2014"
                + System.getProperty("line.separator") + "Loads the NEOCP list from the MPS and provides observations for Find_ORB and also asteroid databases for TheSkyX."
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "Very incomplete list of known bugs:"
                + System.getProperty("line.separator")
                + "1. We include objects on the NEOCP even if they have been moved to the PCCP. Resulting orbits may be plotted incorrectly."
                + System.getProperty("line.separator")
                + "2. Poor error checking. Expect Java stack traces."
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "https://github.com/albertw/NEOCPhelper");
        abouttxt.setWrappingWidth(400);
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("About NEOCPHelper");
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(abouttxt).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();
    }

    // Reference to the main application
    private NEOCPHelper nEOCPHelper;
}
