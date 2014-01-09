/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neocphelperfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import neocphelperfx.model.NEOCP;

/**
 * FXML Controller class
 *
 * @author Albert
 */
public class NEOCPHelperfxController {

    @FXML
    Button newNEOCP;
    @FXML
    Button Observations;
    @FXML
    Button MPCDB;
    @FXML
    Button SmallDB;
    @FXML
    private TableView<NEOCP> NEOCPlist;
    @FXML
    private TableColumn<NEOCP, String> tmpdesigColumn;

    @FXML
    private TableColumn<NEOCP, String> scoreColumn;

    @FXML
    private TableColumn<NEOCP, String> discoveryColumn;

    @FXML
    private TableColumn<NEOCP, String> raColumn;

    @FXML
    private TableColumn<NEOCP, String> decColumn;

    @FXML
    private TableColumn<NEOCP, String> vColumn;

    @FXML
    private TableColumn<NEOCP, String> updatedColumn;

    @FXML
    private TableColumn<NEOCP, String> noteColumn;

    @FXML
    private TableColumn<NEOCP, String> observationsColumn;

    @FXML
    private TableColumn<NEOCP, String> arcColumn;

    @FXML
    private TableColumn<NEOCP, String> hColumn;

    // Reference to the main application
    private NEOCPHelperFX nEOCPHelperFX;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public NEOCPHelperfxController() {
    }

    /**
     * Initializes the controller class.
     *
     */
    @FXML
    private void initialize() {
        tmpdesigColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("tmpdesig"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("score"));
        discoveryColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("discovery"));
        raColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("ra"));
        decColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("dec"));
        vColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("v"));
        updatedColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("updated"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("note"));
        observationsColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("observations"));
        arcColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("arc"));
        hColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("h"));
        // Auto resize columns
        //NEOCPlist.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void newNEOCPFired(ActionEvent event) {

        nEOCPHelperFX.clearNEOCP();
        //NEOCPlist.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        /*lineColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("mpcline"));

         ListView<String> list = new ListView<String>();
         ObservableList<String> items = FXCollections.observableArrayList(
         "Single", "Double", "Suite", "Family App");
         list.setItems(items);
         */
        System.out.println("Pushed new NEOCP button.");
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param nEOCPHelperFX
     */
    public void setMainApp(NEOCPHelperFX nEOCPHelperFX) {
        this.nEOCPHelperFX = nEOCPHelperFX;
        // Add observable list data to the table
        NEOCPlist.setItems(nEOCPHelperFX.getNEOCPData());
        System.out.println("in setMainApp:");
        System.out.println(nEOCPHelperFX.getNEOCPData());
    }

}