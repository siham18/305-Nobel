/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import fxmlexample.CountryClass;
import fxmlexample.LaureatesClass;
import fxmlexample.PrizesClass;
import fxmlexample.Singleton;
import java.util.ArrayList;

/**
 * @author Siham
 *  Purpose: This object is used to hold and sort the information from the API.
 *     It is updated and filled at start using Singleton. The different ArrayLists
 *     hold the Objects for each individual prize, laureate and country. It also
 *     contains an array of ID's which is used to store the laureates that are being
 *     called to view their bio.
 */
public class JsonObject {
   
   //Arrays holding the Objects for the specific categories
   public ArrayList<PrizesClass> prizes = new ArrayList<>();
   public  ArrayList<LaureatesClass> laureates = new ArrayList<>();
   public ArrayList<CountryClass> countries = new ArrayList<>();
   private JsonObject js;
   public ArrayList<Integer> ID = new ArrayList<>();
   
   /*
      When given an ID, returns the LaureatesClass Object that
      Identifies with it, if can't find returns null
   */
   public LaureatesClass getLaur(int ID){
       js = Singleton.getInstance();
       for(int i = 0; i < js.laureates.size(); i++){
           if(js.laureates.get(i).id == ID){
               return js.laureates.get(i);
           }
       }
       return null;   
   }
   
   /*
      Returns the Id last placed into the Array of ID's
   */
   public int getLastId(){
       return ID.get(ID.size() - 1);
   }
   
   /*
      Adds an ID to the Array of ID's
   */
   public void addId(int Id){
       ID.add(Id);
   }
   
   /*
      Given a name, Searches for that names ID in the list of
      Laureates and returns it if found.
      Else returns NULL
   */
   public int searchId(String name){
       js = Singleton.getInstance();
      
      //Here we remove extra brackets from string
       String input = name.substring(1 ,name.length()-1);
       String twoIn = input.trim();
       String oneSearch;
      
      //If the Laureates names are empty, then skip them
       for(int i = 0; i < js.laureates.size(); i++){
           if(js.laureates.get(i).firstname == null && js.laureates.get(i).surname == null){
               System.out.println("Cant find " + js.laureates.get(i).id);
               continue;
           }
           else{
              //If last name is empty look at first and vise versa
               if (js.laureates.get(i).surname == null){
                    oneSearch = (js.laureates.get(i).firstname).trim();
               }
               else if(js.laureates.get(i).firstname == null){
                   oneSearch = (js.laureates.get(i).surname).trim();
               }
               else{
                    oneSearch = (js.laureates.get(i).firstname + " " + js.laureates.get(i).surname).trim();
               }
           }
            //After parsing, check if equal.
            if(twoIn.equals(oneSearch)){
                return js.laureates.get(i).id;
            }
       }
       return 0;
   }
   
   /*
       When given a specific Laureate, searches for the PrizeClass it
       belongs to.
   */
   public PrizesClass getPrize(LaureatesClass lau){
      ArrayList<Integer> listOfIds;
      js = Singleton.getInstance();
      
      //Loop through prizes
      for(int i = 0; i < js.prizes.size(); i++){
          listOfIds = js.prizes.get(i).listOfID();
          
           for(int j = 0; j< listOfIds.size(); j++){
               if(listOfIds.get(j) == lau.id)
                   return js.prizes.get(i);
            }
      }
       return null;
   }
   
   /*
      When given a year and Category, it returns a list
      of LaureateClass that associate with it.
      Returns an empty list if nothing is found
   */
   public ArrayList<LaureatesClass> getLaureateList(String year, String Category){
       js = Singleton.getInstance();
       int len = js.prizes.size();
       ArrayList <LaureatesClass> list = new ArrayList <>();
      
      //If the year is empty then just look for mathcing categories
       if(year == null){
           for(int i = 0; i < len; i++){
               if(js.prizes.get(i).category.equals(Category)){
                   list = js.prizes.get(i).getLaureateList(list);
               }
           }
           return list;
       }
      
      //If year and category are specified, then search for both
       else{
            for(int i = 0; i < len; i++){
           String year2 = "" + this.prizes.get(i).year;
           if(js.prizes.get(i).category.equals(Category) && year2.equals(year)){
                   list = js.prizes.get(i).getLaureateList(list);
               }
            }
            return list;
       } 
    }
   
   /**
     *
     * @return a list of all the laureates
     * @author kimelkins
     */
    public ArrayList<LaureatesClass> getLaureates(){
        js = Singleton.getInstance();
        int len = js.prizes.size();
        
        ArrayList <LaureatesClass> list = new ArrayList <>();
        for(int i = 0; i < len; i++){
            list = js.prizes.get(i).getLaureateList(list);
        }
        return list;
    }
}
