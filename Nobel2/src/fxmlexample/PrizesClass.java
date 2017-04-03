/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;
import fxmlexample.LaureatesClass;
import java.util.ArrayList;
import fxmlexample.LaureatesClass;
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
    public  ArrayList<LaureatesClass> laureates = new ArrayList<>();
     
    /*
        Returns true if the Laureate name is located in the
        specific prize object
    */
    public boolean getLaureate(String fname){
        for(int i = 0; i < this.laureates.size(); i++){
            if(this.laureates.get(i).firstname.equals(fname))
                return true;
        }
        return false;
    }
    
    /*
        Returns a list of Id's that are associated with
        the specific prize class.
    */
     public ArrayList<Integer> listOfID(){
         ArrayList<Integer> list =  new ArrayList<>();
         for(int i =0; i< this.laureates.size(); i++){
             list.add(this.laureates.get(i).id);
         }
         return list;
     }
    
    /*
        Returns the list of laureates.
    */
    public ArrayList <LaureatesClass> getLaureateList(ArrayList <LaureatesClass> list){
        for(int i = 0; i < this.laureates.size(); i++){
           list.add(this.laureates.get(i));
        }
        
        return list;
    }
}
