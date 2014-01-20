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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import neocphelper.model.NEOCP;
import neocphelper.model.SkyXConnection;

/**
 * FXML Controller class
 *
 * @author Albert
 */
public class NEOCPHelperController {

    @FXML
    Button newNEOCP;
    @FXML
    Button FindOrb;
    @FXML
    Button MPCDB;
    @FXML
    Button SmallDB;
    @FXML
    Button SkyXConnect;
    @FXML
    Label Status;

    @FXML
    private TableView<NEOCP> NEOCPlist;
    @FXML
    private TableColumn<NEOCP, String> tmpdesigColumn;
    @FXML
    private TableColumn<NEOCP, Integer> scoreColumn;
    @FXML
    private TableColumn<NEOCP, String> discoveryColumn;
    @FXML
    private TableColumn<NEOCP, Float> raColumn;
    @FXML
    private TableColumn<NEOCP, String> decColumn;
    @FXML
    private TableColumn<NEOCP, Double> altColumn;
    @FXML
    private TableColumn<NEOCP, Double> azColumn;
    @FXML
    private TableColumn<NEOCP, String> vColumn;
    @FXML
    private TableColumn<NEOCP, Double> rateColumn;
    @FXML
    private TableColumn<NEOCP, Double> paColumn;
    @FXML
    private TableColumn<NEOCP, String> updatedColumn;
    @FXML
    private TableColumn<NEOCP, String> noteColumn;
    @FXML
    private TableColumn<NEOCP, Integer> observationsColumn;
    @FXML
    private TableColumn<NEOCP, Float> arcColumn;
    @FXML
    private TableColumn<NEOCP, Float> hColumn;

    // Reference to the main application
    private NEOCPHelper nEOCPHelper;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public NEOCPHelperController() {
    }

    /**
     * Initializes the controller class.
     *
     */
    @FXML
    private void initialize() {
        tmpdesigColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("tmpdesig"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Integer>("score"));
        discoveryColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("discovery"));
        raColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Float>("ra"));
        decColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("dec"));
        altColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Double>("alt"));
        azColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Double>("az"));
        vColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("v"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Double>("rate"));
        paColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Double>("pa"));
        updatedColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("updated"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("note"));
        observationsColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Integer>("observations"));
        arcColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Float>("arc"));
        hColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Float>("h"));
        // Auto resize columns
        //NEOCPlist.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        NEOCPlist.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     *
     * @param event
     */
    @FXML
    public void newSmallDBFired(ActionEvent event) {
        nEOCPHelper.genSmallDB();
        Status.setText("Created TheSkyX Small Asteroid database file.");
    }

    @FXML
    public void newLargeDBFired(ActionEvent event) {
        nEOCPHelper.genLargeDB();
        Status.setText("Created TheSkyX Large Asteroid database file.");
    }

    @FXML
    public void newFindOrbFired(ActionEvent event) {
        nEOCPHelper.printFindOrb();
        Status.setText("Created FindOrb observations file.");
    }

    @FXML
    public void connectSkyXFired(ActionEvent event) throws InterruptedException {
        SkyXConnection skyxconn = nEOCPHelper.getskyxconn();
        try {
            skyxconn.testconnection();
            Status.setText("Updated available data from TheSkyX.");
            nEOCPHelper.setSkyX(true);
            nEOCPHelper.updateNEOCP();
            refreshNEOCPTable();
        } catch (IOException e) {
            Status.setText("Connection failed.");
            Status.setTextFill(Paint.valueOf("red"));
            nEOCPHelper.setSkyX(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NEOCPHelperController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void newNEOCPFired(ActionEvent event) {
        nEOCPHelper.getNEOCP();
        Status.setText("NEOcp table updated from MPC.");

        //NEOCPlist.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        /*lineColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("mpcline"));

         ListView<String> list = new ListView<String>();
         ObservableList<String> items = FXCollections.observableArrayList(
         "Single", "Double", "Suite", "Family App");
         list.setItems(items);
         */
        System.out.println("Pushed new NEOCP button.");
    }

    @FXML
    private void handleDeleteNEO() {
        ObservableList<Integer> indeces = NEOCPlist.getSelectionModel().getSelectedIndices();
        System.out.println(indeces);
        for (int index : indeces) {
            System.out.println(index);
            Status.setText("Deleted NEO " + NEOCPlist.getItems().get(index).getTmpdesig() + " (known bug with multiple deletes)");
            NEOCPlist.getItems().remove(index);
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param nEOCPHelper
     */
    public void setMainApp(NEOCPHelper nEOCPHelper) {
        this.nEOCPHelper = nEOCPHelper;
        // Add observable list data to the table
        NEOCPlist.setItems(nEOCPHelper.getNEOCPData());
        System.out.println("in setMainApp:");
        System.out.println(nEOCPHelper.getNEOCPData());
    }

    private void refreshNEOCPTable() {
        int selectedIndex = NEOCPlist.getSelectionModel().getSelectedIndex();
        NEOCPlist.setItems(null);
        NEOCPlist.layout();
        NEOCPlist.setItems(nEOCPHelper.getNEOCPData());
        // Must set the selected index again (see http://javafx-jira.kenai.com/browse/RT-26291)
        NEOCPlist.getSelectionModel().select(selectedIndex);
    }
}
