/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import java.io.IOException;
import java.net.MalformedURLException;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


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
//    @FXML private TableColumn pCat;
//    @FXML private TableColumn pSharing;
//    @FXML private TableColumn pLau;
//    @FXML private TableColumn pYear;
        
    public String cYearFrom;
    public String cYearTo;
    public String cCategory;
    public ArrayList<LaureatesClass> cLau;
    public Laureate data;
    
    private JsonObject singleton;
    
    ObservableList<String> lCategory = FXCollections.observableArrayList(
            "physics", "peace", "literature", "economics", "medicine", "chemistry");
    //ObservableList<String> lSharing = FXCollections.observableArrayList("1", "2","3","4");
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
     * Displays the found data and fills the columns
     * @param event the mouse click
     * @throws Exception
     */
    @FXML public void search(ActionEvent event) throws Exception{
      correctInput();
      
    }
    
    public void fillList() throws IOException, IOException, MalformedURLException, ParseException, IllegalArgumentException, IllegalAccessException{
        everyoneList.clear();
        fetchData();
        
    }
    
    public ArrayList<PrizesClass> fetchData(){
        int len = singleton.prizes.size();
        for(int i = 0; i <= len; i++){
            everyoneList.add(singleton.prizes.get(i));
        }
        //System.out.println("Prizes: " + PrizesClass.getCategory());
        return singleton.prizes;
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
    
    public void getLau(){
        everyoneList = singleton.getPrizes(cYearTo, cCategory);
        System.out.println("EveryoneList: " + everyoneList);
        //Prizes prize = new Prizes((JSONObject) singleton);
        System.out.println("Prizes: " );
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
    
    public PrizesClass getCat(){
        LaureatesClass laureate = singleton.getLaur(singleton.getLastId());
        PrizesClass prize = singleton.getPrize(laureate);
        return prize;
    }

    @FXML public void getData(ActionEvent event)throws Exception{
        correctInput();
        
        ArrayList <LaureatesClass> laur = singleton.getLaureateList(cYearTo, cCategory);
        ObservableList <String> LaurList = FXCollections.observableArrayList();
        
        
          for(int i = 0; i < laur.size(); i++){
              System.out.println(laur.get(i).surname);
            if(laur.get(i).surname == null){
                LaurList.add(laur.get(i).firstname );
                System.out.println(laur.get(i).motivation);
            }
            else
                LaurList.add(laur.get(i).firstname + " " + laur.get(i).surname );
          }
          list.setItems(LaurList);
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
    
}
