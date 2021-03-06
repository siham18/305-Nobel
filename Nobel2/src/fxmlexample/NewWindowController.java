/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 * 
 * @author Graham
 */
public class NewWindowController implements Initializable{
    ObservableList<Laureate> everyoneList = FXCollections.observableArrayList();
    ObservableList<Laureate> anotherList = FXCollections.observableArrayList();
    @FXML private ListView choices;
    @FXML private ToggleGroup gender;
    @FXML private Label yearlabel, yearTolabel;
    @FXML private Button showbio;
    @FXML private RadioButton male, female, org;
    @FXML private TableView<Laureate> table;
    @FXML private TextField fieldText;
    @FXML private Slider date, dateTo;
    @FXML private ComboBox comboField;
    @FXML private TableColumn<Laureate, String> genderCol, yearCol, categoryCol, bornDateCol,
            diedDateCol, bornCountryCol, diedCountryCol, bornCityCol,
            diedCityCol, firstnameCol, surnameCol;
    
    ObservableList<String> content = FXCollections.observableArrayList(
            "gender", "year", "category", "bornDate", "diedDate", 
            "bornCountry", "diedCountry", "bornCity", "diedCity", "firstname", "surname");
    /**
     * Takes control back to the frontpage
     * @param event when back button clicked
     * @throws IOException 
     */
    @FXML void newPage (ActionEvent event) throws IOException {
        Parent queryResult = FXMLLoader.load(getClass().getResource("Front.fxml"));
        Scene newScene = new Scene(queryResult);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
    /**
     * Opens a pop-up window to show the bio-page for a specific laureate
     * @param event on click
     * @throws Exception 
     */
    @FXML public void openLaureate(ActionEvent event) throws Exception{

        FXMLLoader fxmll = new FXMLLoader(getClass().getResource("Bio.fxml"));
        Parent queryResult = (Parent)fxmll.load();
        
        Laureate chosen = table.getSelectionModel().getSelectedItem();
        
        BioController controller = fxmll.<BioController>getController();
        controller.picData(chosen);
        controller.fill(chosen);
        
        Scene newScene = new Scene(queryResult);
        Stage stage = new Stage();
        stage.setScene(newScene);
       
        stage.show();
    }
    /**
     * a helper function to get value
     */
    public void fillValue(){
        choices.getSelectionModel().getSelectedItem();
    }
    /**
     * fills the columns of the tableview. It used to be a dynamic table
     */
    public void inputData(){
        table.getColumns().get(0).setVisible(false);
        table.getColumns().get(0).setVisible(true);
        table.getItems().clear();
        table.getItems().addAll(everyoneList);  
    }
    /**
     * clears the table
     * @throws IOException
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParseException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public void fillList() throws IOException, IOException, MalformedURLException, ParseException, IllegalArgumentException, IllegalAccessException{
        everyoneList.clear();
        fetchData();
    }
    /**
     * combines the clear and and fill table methods
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParseException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public void newSearch() throws IOException, MalformedURLException, ParseException, IllegalArgumentException, IllegalAccessException {
        fillList();
        inputData();
    }
    /**
     * A narrowed search. It takes the displayed results, and performs
     * a second search on those values.
     * @throws IOException
     * @throws IOException
     * @throws MalformedURLException
     * @throws ParseException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public void addSearch() throws IOException, IOException, MalformedURLException, ParseException, IllegalArgumentException, IllegalAccessException{
        Set<Laureate> list = new HashSet<>();
        Set<Laureate> list2 = new HashSet<>();
        for (Laureate each : everyoneList){
            list.add(each);
        }
        fillList();
        for (Laureate each : everyoneList){
            list2.add(each);
        }
        list.retainAll(list2);
        everyoneList.clear();
        for (Laureate each : list){
            everyoneList.add(each);
        }
        
        inputData();
    }
    /**
     * Performs the JSON request to the nobelprize website.
     * @throws MalformedURLException
     * @throws IOException
     * @throws ParseException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public void fetchData() throws MalformedURLException, IOException, ParseException, IllegalArgumentException, IllegalAccessException{
        String url = "http://api.nobelprize.org/v1/";
        url += getInput();
        
        URL nobelUrl = new URL(url);
        URLConnection con = nobelUrl.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        JSONParser parser = new JSONParser();

        inputLine = in.readLine();
        
        JSONObject thing = (JSONObject) parser.parse(inputLine);
        JSONArray list = (JSONArray)thing.get("laureates");
        for (Object each : list){
            Laureate person = new Laureate((JSONObject)each);
            person.setLaureate();
            if ("firstname".equals(choices.getSelectionModel().getSelectedItem())){
                if(person.getFirstname().contains(fieldText.getText())){
                    everyoneList.add(person);
                }
            } else if ("surname".equals(choices.getSelectionModel().getSelectedItem())){
                if(person.getSurname().contains(fieldText.getText())){
                    everyoneList.add(person);
                }
            } else if ("category".equals(choices.getSelectionModel().getSelectedItem())){
                if(person.getCategory().contains(comboField.getSelectionModel().getSelectedItem().toString()))
                    everyoneList.add(person);
            }   else if  ("year".equals(choices.getSelectionModel().getSelectedItem())){
                if(Integer.valueOf(person.getYear()) >=(((int)Math.round(date.getValue()))) && 
                        Integer.valueOf(person.getYear()) <=(((int)Math.round(dateTo.getValue())))){
                    everyoneList.add(person);
                }
            } else if  ("bornDate".equals(choices.getSelectionModel().getSelectedItem())){
                if(Integer.valueOf(person.getBornDate().substring(0,4)) >=(((int)Math.round(date.getValue()))) && 
                        Integer.valueOf(person.getBornDate().substring(0,4)) <=(((int)Math.round(dateTo.getValue())))){
                    everyoneList.add(person);
                }
            } else if  ("diedDate".equals(choices.getSelectionModel().getSelectedItem())){
                if(Integer.valueOf(person.getDiedDate().substring(0,4)) >=(((int)Math.round(date.getValue()))) && 
                        Integer.valueOf(person.getDiedDate().substring(0,4)) <=(((int)Math.round(dateTo.getValue())))){
                    everyoneList.add(person);
                }
            } else {
                everyoneList.add(person);
            }
            
        }
    }
    /**
     * Checks the input lines to see what the string needs to be.
     * @returns a string for the JSON request 
     */
    private String getInput() {
        String returnValue = "laureate.json?";
        if ("gender".equals(choices.getSelectionModel().getSelectedItem())){
            returnValue += "gender=" + gender.getSelectedToggle().getUserData();
        }
        else if("bornDate".equals(choices.getSelectionModel().getSelectedItem())){
            returnValue += "bornDate=" + Math.round(date.getValue()) + "&bornDateTo=" + Math.round(dateTo.getValue());
        }
        else if ("diedDate".equals(choices.getSelectionModel().getSelectedItem())){
            returnValue += "diedDate=" + Math.round(date.getValue()) + "&diedDateTo=" + Math.round(dateTo.getValue());
        }
        else if ("firstname".equals(choices.getSelectionModel().getSelectedItem())){
            ;
        }
        else if ("surtname".equals(choices.getSelectionModel().getSelectedItem())){
            ;
        }
        else if ("diedCity".equals(choices.getSelectionModel().getSelectedItem())){
            returnValue += "diedCity=" + fieldText.getText();
        }
        else if ("bornCity".equals(choices.getSelectionModel().getSelectedItem())){
            returnValue += "bornCity=" + fieldText.getText();
        }
        else if ("diedCountry".equals(choices.getSelectionModel().getSelectedItem())){
            returnValue += "diedCountry=" + fieldText.getText();
        }
        else if ("bornCountry".equals(choices.getSelectionModel().getSelectedItem())){
            returnValue += "bornCountry=" + fieldText.getText();
        }
        return returnValue;
    }   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choices.getItems().setAll(content);
        male.setToggleGroup(gender);
        male.setUserData("male");
        female.setUserData("female");
        org.setToggleGroup(gender);
        org.setUserData("org");
        female.setToggleGroup(gender);
        for (Object each: table.getColumns()){
          TableColumn col = (TableColumn)each;
          if(content.contains(col.getText())){
              col.setVisible(true);
          }
      }
        
        genderCol.setCellValueFactory(new PropertyValueFactory("gender"));
        yearCol.setCellValueFactory(new PropertyValueFactory("year"));
        categoryCol.setCellValueFactory(new PropertyValueFactory("category"));
        bornDateCol.setCellValueFactory(new PropertyValueFactory("bornDate"));
        diedDateCol.setCellValueFactory(new PropertyValueFactory("diedDate"));
        bornCountryCol.setCellValueFactory(new PropertyValueFactory("bornCountry"));
        diedCountryCol.setCellValueFactory(new PropertyValueFactory("diedCountry"));
        bornCityCol.setCellValueFactory(new PropertyValueFactory("bornCity"));
        diedCityCol.setCellValueFactory(new PropertyValueFactory("diedCity"));
        firstnameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
        surnameCol.setCellValueFactory(new PropertyValueFactory("surname"));
        
        date.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2){
                yearlabel.setText(String.valueOf((int)date.getValue()+0));
            }
        });
         dateTo.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2){
                yearTolabel.setText(String.valueOf((int)dateTo.getValue()+0));
            }
        });
         table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Laureate>() {
            @Override
            public void changed(ObservableValue<? extends Laureate> observable, Laureate oldValue, Laureate newValue) {
                if (!newValue.equals(null)){
                    showbio.setVisible(true);
                } else{
                    showbio.setVisible(false);
                }}
         });
         
        choices.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if ("category".equals(newValue)){
                comboField.getItems().clear();
                comboField.getItems().addAll("physics", "medicine", "peace", "economics", "chemistry", "literature");
            }
            if ("bornCountry".equals(newValue) || "diedCountry".equals(newValue) || 
                    "bornCity".equals(newValue) || "diedCity".equals(newValue)||
                    "surname".equals(newValue) || "firstname".equals(newValue)){
                fieldText.setVisible(true);
                fieldText.setText("**Insert text**");
            }
            else {
                fieldText.setVisible(false);
            }
            if (!"gender".equals(newValue)){
                male.setVisible(false);
                female.setVisible(false);
                org.setVisible(false);
            }
            else{
                male.setVisible(true);
                female.setVisible(true);
                org.setVisible(true);
            }
            if ("bornDate".equals(newValue) || "diedDate".equals(newValue) || "year".equals(newValue)){
                date.setVisible(true);
                dateTo.setVisible(true);
                yearlabel.setVisible(true);
                yearTolabel.setVisible(true);
                yearlabel.setText(Double.toString(date.getValue()));
                yearTolabel.setText(Double.toString(dateTo.getValue()));
            }
            else{
                date.setVisible(false);
                dateTo.setVisible(false);
                yearlabel.setVisible(false);
                yearTolabel.setVisible(false);
            }
            if ("category".equals(newValue)){
                comboField.setVisible(true);
            }
            else {
                comboField.setVisible(false);
            }
        }
        });
        }
}    
    



