/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import fxmlexample.ImageCl;
import fxmlexample.JsonObject;
import fxmlexample.LaureatesClass;
import fxmlexample.PrizesClass;
import fxmlexample.Singleton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import fxmlexample.ImageCl;
import fxmlexample.JsonObject;
import fxmlexample.LaureatesClass;
import fxmlexample.PrizesClass;

/**
 * FXML Controller class
 *
 * @author Siham
 */
public class WinnerController implements Initializable {

    @FXML
    private ImageView pic4;
    @FXML public Label namef, FieldT, MotifT, ResT, bornT, title;
    //@FXML private Text namef;
    @FXML public LaureatesClass laur;
    @FXML public PrizesClass prize;
    JsonObject singleton;
    public static int ID;
    /**
     * Initializes the controller class.
     */
    
    public void fill(){
        singleton = Singleton.getInstance();
        laur = singleton.getLaur(singleton.getLastId());
        System.out.println(" -- " + laur.firstname + " -- ");
        prize = singleton.getPrize(laur);
        //namef.setText(laur.firstname +" " +laur.surname);
        FieldT.setText(prize.category);
        MotifT.setText(laur.motivation);
        ResT.setText(laur.bornCountry);
        bornT.setText(laur.bornDate +", " + laur.bornCity);
        title.setText(laur.firstname + " "+laur.surname);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fill();
        if(prize!= null){
            String n = laur.surname;
            if(laur.surname!=null){
                 n = laur.surname.replace(" ", "_");
            }
            else{
                n = laur.firstname;
            }
            ImageCl image = new ImageCl(prize.category, ""+prize.year, n.toLowerCase());
            String im = image.getURL();
            Image image2 = new Image(im);
            
            pic4.setImage(image2);
        }
        else{
            System.out.println("GETS NULL FOR PRIZES");
        }
    }
}
