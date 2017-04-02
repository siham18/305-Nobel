/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Graham
 */
public class BioController implements Initializable {
    @FXML public Label namef, FieldT, MotifT, ResT, bornT, title;
    @FXML private ImageView pic4;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void picData(Laureate chosen){
        ImageCl image = new ImageCl(chosen.getCategory(), chosen.getYear(), chosen.getSurname().toLowerCase());
        String im = image.getURL();
        Image image2 = new Image(im);
        System.out.println(im);
        pic4.setImage(image2);
   }
    public void fill(Laureate chosen){
        FieldT.setText(chosen.getCategory());
        MotifT.setText(chosen.getMotivation());
        ResT.setText(chosen.getBornCountry());
        bornT.setText(chosen.getBornDate() +", " + chosen.getBornCity());
        title.setText(chosen.getFirstname() + " "+chosen.getSurname().replace(" ", "_"));
    }
}
