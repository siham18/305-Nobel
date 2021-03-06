package fxmlexample;

import fxmlexample.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class: Main page contains buttons to access the other pages
 **/

public class FrontController implements Initializable {

    @FXML private Button general;
    @FXML private Button prize;
    @FXML private Button lau;
    @FXML public ImageView im2;
    @FXML public ImageView im1;

     /**
     * Opens the General search form
     * @param event the mouse click
     * @throws Exception
     * @author Graham
     */
    @FXML public void openGeneral(ActionEvent event) throws Exception{
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("NewWindow.fxml"));
        Parent queryResult = (Parent)fxml.load();
        
        Scene newScene = new Scene(queryResult);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
    
     /**
     * Opens the Laureates search form
     * @param event the mouse click
     * @throws Exception
     * @author Siham
     */
     @FXML public void openLaureate(ActionEvent event) throws Exception{
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("LaureateSearch.fxml"));
        Parent queryResult = (Parent)fxml.load();
        
        Scene newScene = new Scene(queryResult);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
     
     /**
     * Opens the prize search form
     * @param event the mouse click
     * @throws Exception
     * @author kimelkins
     */
    @FXML public void openPrize(ActionEvent event) throws Exception{
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("PrizesSearch.fxml"));
        Parent queryResult = (Parent)fxml.load();
        
        Scene newScene = new Scene(queryResult);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }
  
     //Loads pictures for front
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JsonObject pic = Singleton.getInstance();
        im2.setImage(Singleton.image2);
        im1.setImage(Singleton.image1);
    }    
    
}
