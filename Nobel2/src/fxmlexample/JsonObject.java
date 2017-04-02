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
 *
 * @author Siham
 */
public class JsonObject {
   
   public ArrayList<PrizesClass> prizes = new ArrayList<>();
   public  ArrayList<LaureatesClass> laureates = new ArrayList<>();
   public ArrayList<CountryClass> countries = new ArrayList<>();
   private JsonObject js;
   public ArrayList<Integer> ID = new ArrayList<>();
   
   public void getPrizesAtYear(int year){
       int size = this.prizes.size();
       for(int i=0; i< size; i++){
           if (this.prizes.get(i).year == year){
        
           }
       }
   }
   
   public LaureatesClass getLaur(int ID){
       js = Singleton.getInstance();
       for(int i = 0; i < js.laureates.size(); i++){
           if(js.laureates.get(i).id == ID){
               return js.laureates.get(i);
           }
       }
       return null;   
   }
   
   public int getLastId(){
       return ID.get(ID.size() - 1);
   }
   
   public void addId(int Id){
       ID.add(Id);
   }
   
   public int searchId(String name){
       js = Singleton.getInstance();
       String input = name.substring(1 ,name.length()-1);
       String twoIn = input.trim();
       String oneSearch;
       for(int i = 0; i < js.laureates.size(); i++){
           if(js.laureates.get(i).firstname == null && js.laureates.get(i).surname == null){
               System.out.println("Cant find " + js.laureates.get(i).id);
               continue;
           }
           else{
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
            if(twoIn.equals(oneSearch)){
                System.out.println("\n\n\n\n\n!!!!!!!!!!!!!!!!!--------Helloooooo"+ input +";"+ oneSearch);
                return js.laureates.get(i).id;
            }
       }
       return 0;
   }
   
   //Finds the prizes class for a specific Laureate.
   public PrizesClass getPrize(LaureatesClass lau){
      ArrayList<Integer> listOfIds;
      js = Singleton.getInstance();
      
      for(int i = 0; i < js.prizes.size(); i++){
          listOfIds = js.prizes.get(i).listOfID();
          
           for(int j = 0; j< listOfIds.size(); j++){
               if(listOfIds.get(j) == lau.id)
                   return js.prizes.get(i);
            }
      }
       return null;
   }
   
   public ArrayList<LaureatesClass> getLaureateList(String year, String Category){
       js = Singleton.getInstance();
       int len = js.prizes.size();
       
       ArrayList <LaureatesClass> list = new ArrayList <>();
       if(year == null){
           for(int i = 0; i < len; i++){
               if(js.prizes.get(i).category.equals(Category)){
                   list = js.prizes.get(i).getLaureateList(list);
               }
           }
           return list;
       }
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
