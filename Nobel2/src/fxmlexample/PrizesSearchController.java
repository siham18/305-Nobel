/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author kimelkins
 */
public class PrizesSearchController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private Button back;
    @FXML public ChoiceBox catagory;
    @FXML private TextField yearFrom;
    @FXML private TextField yearTo;
    @FXML private Button open;
    @FXML private Button search;
    @FXML public ChoiceBox numLau;
    @FXML private ListView<String> list;
    @FXML private ListView<String> extraList;
        
    public String cYearFrom;
    public String cYearTo;
    public String cCategory;
    public ArrayList<LaureatesClass> cLau;
    public Laureate data;
    public String temp;
    
    private JsonObject singleton;
    
    ObservableList<String> lCategory = FXCollections.observableArrayList(
            "physics", "peace", "literature", "economics", "medicine", "chemistry");
    public ObservableList<PrizesClass> prizesList = FXCollections.observableArrayList();
    ObservableList<PrizesClass> everyoneList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // Initializes the choiceboxes
        catagory.setItems(lCategory);
        catagory.setValue("Physics");
        
        singleton = Singleton.getInstance(); 
    } 
    
    /**
     * Brings the user back to the home screen 
     * @param event the mouse click
     * @throws Exception
     */
    @FXML public void back(ActionEvent event) throws Exception{
        
       
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("Front.fxml"));
        Parent queryResult = (Parent)fxml.load();
        
        Scene newScene = new Scene(queryResult);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    /**
     * Makes sure the given input is all correct, otherwise sets to null
     */
    public void correctInput(){
    
        if(yearTo.getText().trim().isEmpty() || yearTo.getText() == null ||
            yearTo.getText().length() > 4 || yearTo.getText().length() < 4){
            cYearTo = null;
        }
        else{
            cYearTo = yearTo.getText();
        }
    
        if(yearFrom.getText().trim().isEmpty() || yearFrom.getText() == null ||
            yearFrom.getText().length() > 4 || yearFrom.getText().length() < 4){
            cYearFrom = null;
        }   
        else{
            cYearFrom = yearFrom.getText();
        }
    
        cCategory = catagory.getValue().toString();
    
    }
    
    /**
     * Gets the laureates from the prizes class
     */
    public void getLau(){
        everyoneList = singleton.getPrizes(cYearTo, cCategory);
        System.out.println("EveryoneList: " + everyoneList);
       
    }
   
    /**
     * Gets the year for the laureate given
     * @param lau the laureate that needs its year
     * @return the year for the laureate
     */
    public String getYear(String lau){
        String year = null;
        ArrayList <LaureatesClass> laur = singleton.getLaureateList(cYearTo, cCategory);
        if(cYearTo == null){
            for (LaureatesClass laureate : singleton.laureates) {
                int id = singleton.getLastId();
                year = singleton.getLaurYear(id);
                return year;
            }
        }
        else{
            year = yearTo.toString();
        }
        return year;
    }

    /**
     * The search button is connected to it; gets and displays everything to the list
     * @param event mouse click
     * @throws Exception
     */
    @FXML public void getData(ActionEvent event)throws Exception{
        correctInput();
        //Makes sure there is input in the yearTo field
        if(cYearTo == null){
            cYearTo = "2016";
        }
        
        // creates lists for parsing
        ArrayList <LaureatesClass> laur = singleton.getLaureateList(cYearTo, cCategory);
        ObservableList <String> LaurList = FXCollections.observableArrayList(); // the list with the names of the laureates
        ObservableList <String> extraLaurList = FXCollections.observableArrayList(); // the list for the laureates other info
        
        // goes through all the laureates
        for(int i = 0; i < laur.size(); i++){
            
            //Checks if thers a surname or not and if not will just add the 
            // first name to the list to display
            if(laur.get(i).surname == null){
                LaurList.add(laur.get(i).firstname );
                System.out.println(laur.get(i).motivation);
                extraLaurList.add(cCategory + "-----" + cYearTo);
        }
        else{
            LaurList.add(laur.get(i).firstname + " " + laur.get(i).surname );
            extraLaurList.add(cCategory+ "-----" + cYearTo);
        }
        // displays the lists to their listViews
        list.setItems(LaurList);
        extraList.setItems(extraLaurList);
        }
        
    }   
    
    /**
     * Opens the selected laureates bio
     * @param event mouse click
     * @throws Exception
     */
    @FXML public void openLaureate(ActionEvent event) throws Exception{
        

        String temp = list.getSelectionModel().getSelectedItems().toString();
        
        int tempp = singleton.searchId(temp);
        if(tempp == 0){
            System.out.println("Cant find id" + temp);
            return;
        }
        singleton.addId(tempp);
          
        FXMLLoader fxmll = new FXMLLoader(getClass().getResource("winner.fxml"));
        Parent root1 = (Parent) fxmll.load();
        Stage stage = new Stage();
     
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    
}
