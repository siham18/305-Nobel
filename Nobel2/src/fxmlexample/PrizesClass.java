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
 */
public class PrizesClass {
    
    public int year;
    public int yearTo;
    public String category;
    public String firstname;
    public String surname;
    public  ArrayList<LaureatesClass> laureates = new ArrayList<>();
    //public ArrayList<Laureates> laureates = new ArrayList<>();
     
    
    /*
    checks to make sure you have the right laureate
    */
    public boolean getLaureate(String fname){
        for(int i = 0; i < this.laureates.size(); i++){
            if(this.laureates.get(i).firstname.equals(fname))
                return true;
        }
        return false;
    }
    
    /*
    gets a list of ID's and returns the list
    */
     public ArrayList<Integer> listOfID(){
        
         ArrayList<Integer> list =  new ArrayList<>();
         for(int i =0; i< this.laureates.size(); i++){
             list.add(this.laureates.get(i).id);
         }
         return list;
     }
    
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
           
        }
        
        return list;
    }
    
    
}
