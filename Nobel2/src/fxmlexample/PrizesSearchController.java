/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.simple.JSONArray;


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
    @FXML private ListView list;
    @FXML private TableColumn pCat;
    @FXML private TableColumn pSharing;
    @FXML private TableColumn pLau;
    @FXML private TableColumn pYear;
        
    public String cYearFrom;
    public String cYearTo;
    public String cCategory;
    public ArrayList<LaureatesClass> cLau;
    public Laureate data;
    
    private JsonObject singleton;
    
    ObservableList<String> lCategory = FXCollections.observableArrayList(
            "Physics", "Peace", "Literature", "Economics", "Medicine", "Chemistry");
    //ObservableList<String> lSharing = FXCollections.observableArrayList("1", "2","3","4");
    public ObservableList prizesList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // Initializes the choiceboxes
        
//        catagory.getItems().clear();
//        catagory.getItems().addAll("Physics", "Peace", "Literature", "Economics", "Medicine", "Chemistry");
        catagory.setItems(lCategory);
        catagory.setValue("Physics");
//        numLau.getItems().clear();
//        numLau.getItems().addAll("1", "2","3","4");
        //numLau.setItems(lSharing);
        //numLau.setValue("1");
        
        singleton = Singleton.getInstance(); 
        
        pCat.setCellValueFactory(new PropertyValueFactory("categoryCol"));
        //pSharing.setCellValueFactory(new PropertyValueFactory("sharingCol"));
        pLau.setCellValueFactory(new PropertyValueFactory("laureateCol"));
        pYear.setCellValueFactory(new PropertyValueFactory("yearWonCol"));
        //pYearFrom.setCellValueFactory(new PropertyValueFactory("yearFromCol"));
        
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
     * Displays the found data and fills the columns
     * @param event the mouse click
     * @throws Exception
     */
    @FXML public void search(ActionEvent event) throws Exception{
      correctInput();
      
      
      
    }
    
    /**
     * Opens up the selected laureates information
     * @param event
     * @throws Exception
     */
    @FXML public void open(ActionEvent event) throws Exception{
        
    }

    /**
     * Makes sure the given input is all correct, otherwise sets to null
     */
    public void correctInput(){
    
        if(yearTo.getText().trim().isEmpty() || yearTo.getText() == null ||
            yearTo.getText().length() == 4){
            cYearTo = null;
        }
        else{
            cYearTo = yearTo.getText();
        }
    
        if(yearFrom.getText().trim().isEmpty() || yearFrom.getText() == null ||
            yearFrom.getText().length() == 4){
            cYearFrom = null;
        }   
        else{
            cYearFrom = yearFrom.getText();
        }
    
        //cSharing = numLau.getValue().toString();
        cCategory = catagory.getValue().toString();
    
    }
    
    
    public ArrayList<LaureatesClass> getLau(){
        return singleton.getLaureates();
        
    }
    
    public boolean getYear(int year){
        int sYearFrom = Integer.parseInt(cYearFrom);
        
        if(cYearFrom == null){
            sYearFrom = 0;
        }
        int sYearTo = Integer.parseInt(cYearTo);
        
        if(cYearTo == null){
            sYearTo = 0;
        }
        return year >= sYearFrom && year <= sYearTo;
    }

    public void getData(){
        ObservableList dataList = FXCollections.observableArrayList();
        ArrayList<LaureatesClass> laur = getLau();
        int len = laur.size();
        
        for(int i = 0; i < len; i++){
            if(laur.get(i).category.equals(cCategory) && getYear(laur.get(i).year)){
                String name = laur.get(i).firstname + " " + laur.get(i).surname;
                pCat.getColumns().add(laur.get(i).category);
                pLau.getColumns().add(name);
                pYear.getColumns().add(laur.get(i).year);
                //dataList.add(laur.get(i).category);
                //dataList.add(name);
                //dataList.add(laur.get(i).year);
                
            }
            //prizesList.add(dataList);
            //dataList = null;
        }
        
        
    }
    
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
    public void setData(){
        
    }
}
