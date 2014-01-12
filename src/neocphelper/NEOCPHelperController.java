/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neocphelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import neocphelper.model.NEOCP;

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
    private TableColumn<NEOCP, String> vColumn;

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
        vColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("v"));
        updatedColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("updated"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, String>("note"));
        observationsColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Integer>("observations"));
        arcColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Float>("arc"));
        hColumn.setCellValueFactory(new PropertyValueFactory<NEOCP, Float>("h"));
        // Auto resize columns
        //NEOCPlist.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void newSmallDBFired(ActionEvent event){
        nEOCPHelper.genSmallDB();
        System.out.println("Pushed new Small DB Button.");
    }

    public void newLargeDBFired(ActionEvent event){
        nEOCPHelper.genLargeDB();
        System.out.println("Pushed new Large DB Button.");
    }
    
    public void newFindOrbFired(ActionEvent event){
        nEOCPHelper.printFindOrb();
        System.out.println("Pushed new FindOrb button.");
    }
    /*
    public void newAboutFired(ActionEvent event){
        System.out.println("Pushed about button.");
    }
    */
    
    public void newNEOCPFired(ActionEvent event) {

        nEOCPHelper.getNEOCP();
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
     * @param nEOCPHelper
     */
    public void setMainApp(NEOCPHelper nEOCPHelper) {
        this.nEOCPHelper = nEOCPHelper;
        // Add observable list data to the table
        NEOCPlist.setItems(nEOCPHelper.getNEOCPData());
        System.out.println("in setMainApp:");
        System.out.println(nEOCPHelper.getNEOCPData());
    }

}
