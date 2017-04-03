/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import fxmlexample.JsonObject;
import fxmlexample.LaureatesClass;
import fxmlexample.Singleton;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * @author Siham Salah
 * This is where the main Search happens for Laureate and where
 *  the page comes up after the Laureate button is clicked on the front Page.
 *  It contains two buttons, one for searching the list of laureates that is specified
 *  at in the text boxes, and one for popping up the laureate box that gives information
 *  about the laureate.
 */
public class LaureateSearchController implements Initializable{

    ObservableList<String> names = FXCollections.observableArrayList(
            "physics", "peace", "literature", "economics", "medicine", "chemistry");
    
    @FXML private TextField year;
    @FXML private TextField Fname;
    @FXML private TextField Lname;
    @FXML private AnchorPane Pane;
    @FXML public ChoiceBox Dropdown;
    @FXML public ChoiceBox FirstN;
    @FXML private Button back;
    @FXML private Button search;
    @FXML private Button open;
    @FXML private ListView<String> list;
    public String chosenY;
    public String chosenN;
    public String chosenC;
    @FXML ListView<String> listView;
    private JsonObject singleton;
    @FXML private TableView <LaureatesClass> tableView;
    @FXML private TableColumn <LaureatesClass, String> name, lastname, motivation;
    
    /*
        Sets the Dropdown menue to the variables in the array names, then
        sets singleton to the Json Object from the startup of the application
    */ 
    @FXML public void initialize(URL urll, ResourceBundle rb){
       Dropdown.setItems(names);
       Dropdown.setValue("peace");
       singleton = Singleton.getInstance();  
    }
    
    /*
        This is where the input selected for Year is parsed and
        the is stored in the globale variables
    */
    public void listv(){
       if(year.getText().trim().isEmpty() || year.getText() == null || 
             year.getText().length() > 4 || year.getText().length() < 4){
           chosenY = null;
       }
       else
            chosenY = year.getText();
        
      chosenC = Dropdown.getValue().toString();
    }
    
    /*
      This method is called when the user wants to go back to
      the main page.
    */
    @FXML public void back(ActionEvent event) throws Exception{
    
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("Front.fxml"));
        Parent queryResult = (Parent)fxml.load();
        
        Scene newScene = new Scene(queryResult);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
     
    /*
        This Method is called when the user clicks on a laureate and 
        Wants to know more information from them. It opens winner.fxml
        in an extra window and adds the ID of the laurete they would like to
        see to an array in the JsonObject that will be referenced later in Winner.
    */
      @FXML public void openLaureate(ActionEvent event) throws Exception{

        String temp = listView.getSelectionModel().getSelectedItems().toString();
        
          //If the Laureate cannot be found
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
      /*
        This method populates the lsit view, it takes the input from listv()
        then searches for the list of Laureates that corespond to the 
        that search and places them into the table/
      */
      @FXML public void getValues(ActionEvent event) throws Exception{
          listv();
          
          ArrayList <LaureatesClass> laur = singleton.getLaureateList(chosenY, chosenC);
          ObservableList <String> everyoneList = FXCollections.observableArrayList();
          
          for(int i = 0; i < laur.size(); i++){
            if(laur.get(i).surname == null){
                everyoneList.add(laur.get(i).firstname);
                System.out.println(laur.get(i).motivation);
            }
            else
                everyoneList.add(laur.get(i).firstname + " " + laur.get(i).surname);
          }
          listView.setItems(everyoneList);
    }
}
