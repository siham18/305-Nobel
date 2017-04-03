/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;
import fxmlexample.LaureatesClass;
import java.util.ArrayList;
import fxmlexample.LaureatesClass;
import javafx.collections.ObservableList;
/**
 *
 * @author Siham
 * PrizesClass: this is where the prizes informations are stored,
    an array of this Object is listed in JsonObject
 */
public class PrizesClass {
    
    public int year;
    public int yearTo;
    public String category;
    public String firstname;
    public String surname;
    public  ArrayList<LaureatesClass> laureates = new ArrayList<>();
     
<<<<<<< HEAD
    
    /*
    checks to make sure you have the right laureate
=======
    /*
        Returns true if the Laureate name is located in the
        specific prize object
>>>>>>> origin/master
    */
    public boolean getLaureate(String fname){
        for(int i = 0; i < this.laureates.size(); i++){
            if(this.laureates.get(i).firstname.equals(fname))
                return true;
        }
        return false;
    }
    
    /*
<<<<<<< HEAD
    gets a list of ID's and returns the list
=======
        Returns a list of Id's that are associated with
        the specific prize class.
>>>>>>> origin/master
    */
     public ArrayList<Integer> listOfID(){
         ArrayList<Integer> list =  new ArrayList<>();
         for(int i =0; i< this.laureates.size(); i++){
             list.add(this.laureates.get(i).id);
         }
         return list;
     }
    
<<<<<<< HEAD
     // when given a list, it will populate it with all the laureates 
    public ArrayList <LaureatesClass> getLaureateList(ArrayList <LaureatesClass> list){
        
        for(int i = 0; i < this.laureates.size(); i++){
           list.add(this.laureates.get(i));
           
        }
        
        return list;
    }
    
    //when given a list, will return the list full of laureates
    public ObservableList getLauList(ObservableList list){
        
        for(int i = 0; i < this.laureates.size(); i++){
           list.add(this.laureates.get(i));
           
=======
    /*
        Returns the list of laureates.
    */
    public ArrayList <LaureatesClass> getLaureateList(ArrayList <LaureatesClass> list){
        for(int i = 0; i < this.laureates.size(); i++){
           list.add(this.laureates.get(i));
>>>>>>> origin/master
        }
        
        return list;
    }
    
    
}
