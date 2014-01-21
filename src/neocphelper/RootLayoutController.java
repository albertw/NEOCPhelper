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
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Albert
 */
public class RootLayoutController {

    @FXML
    private MenuItem about;

    public void newAboutFired(ActionEvent event) {

        TextArea abouttxt = new TextArea("NEOCPHelper by Albert.white@gmail.com (Z72). Version 1.0"
                + System.getProperty("line.separator")  + System.getProperty("line.separator")
                + "Loads the NEOCP list from the MPC and provides observations "
                + "for Find_ORB and also asteroid databases for TheSkyX."
                + System.getProperty("line.separator")  + System.getProperty("line.separator")
                + "In order for the 'Upadte from TheSkyX' button to populate the data you will need to have a version of TheSkyX with the TCP server enabled and also have previosuiosly loaded the objects in the Small Asteroid Database."
                + System.getProperty("line.separator") + System.getProperty("line.separator")
                + "https://github.com/albertw/NEOCPhelper");
 
        abouttxt.setWrapText(true);
        abouttxt.setEditable(false);

        Stage stage = new Stage();
        StackPane layout = new StackPane();
        layout.getChildren().setAll(abouttxt);
        stage.setTitle("About");
        stage.setScene(new Scene(layout));
        stage.show();
    }

    // Reference to the main application
    private NEOCPHelper nEOCPHelper;
}
