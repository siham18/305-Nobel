/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import java.lang.reflect.Field;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * A class made before GSON was discovered. It takes
 * a JSONObject and finds all the necessary fields and makes
 * a laureate object out of its findings.
 * @author Graham
 */
public class Laureate {
    String firstname;
    String bornCountry;
    String gender;
    String surname;
    String bornCity;
    String diedCountry;
    String diedCity;
    String bornDate;
    String diedDate;
    String motivation;
    String year;
    String category;
    String share;
    
    String affiliation;
    
    public Laureate(JSONObject obj){
        firstname = (String)obj.get("firstname");
        bornCountry = (String)obj.get("bornCountry");
        gender = (String)obj.get("gender");
        surname = (String)obj.get("surname");
        bornCity = (String)obj.get("bornCity");
        diedCountry = (String)obj.get("diedCountry");
        diedCity = (String)obj.get("diedCity");
        bornDate = (String)obj.get("born");
        diedDate = (String)obj.get("died");
        motivation = (String)((JSONObject)((JSONArray)(obj.get("prizes"))).get(0)).get("motivation");
        year = (String)((JSONObject)((JSONArray)(obj.get("prizes"))).get(0)).get("year");
        category = (String)((JSONObject)((JSONArray)(obj.get("prizes"))).get(0)).get("category");
        share = (String)((JSONObject)((JSONArray)(obj.get("prizes"))).get(0)).get("share");
        affiliation = (String)((JSONObject)((JSONArray)(obj.get("prizes"))).get(0)).get("affiliation");
        
    }
    @Override
    public int hashCode(){
        int hashCode = 1;
        hashCode += 1 * this.firstname.length();
        hashCode += 2 * this.bornCountry.length();
        hashCode += 3 * this.gender.length();
        hashCode += 4 * this.surname.length();
        hashCode += 4 * this.bornCity.length();
        hashCode += 5 * this.diedCountry.length();
        hashCode += 6 * this.diedCity.length();
        hashCode += 31 * this.motivation.length();
        hashCode += 19 * this.category.length();
        hashCode += 23 * this.affiliation.length();
        return hashCode;
    }
    @Override
    public boolean equals(Object o){
        boolean equality = true;
        if(! (o instanceof Laureate)) return false;

        Laureate p = (Laureate) o;
        if (!this.firstname.equals(p.getFirstname()))
            return false;
        if (!this.surname.equals(p.getSurname()))
            return false;
        if (!this.bornCountry.equals(p.getBornCountry()))
            return false;
        if (!this.bornCity.equals(p.getBornCity()))
            return false;
        if (!this.diedCountry.equals(p.getDiedCountry()))
            return false;
        if (!this.diedCity.equals(p.getDiedCity()))
            return false;
        if (!this.gender.equals(p.getGender()))
            return false;
        if (!this.bornDate.equals(p.getBornDate()))
            return false;
        if (!this.diedDate.equals(p.getDiedDate()))
            return false;
        if (!this.motivation.equals(p.getMotivation()))
            return false;
        if (!this.year.equals(p.getYear()))
            return false;
        if (!this.category.equals(p.getCategory()))
            return false;
        if (!this.share.equals(p.getShare()))
            return false;
        if (!this.affiliation.equals(p.getAffiliation()))
            return false;
        return equality;
    }
    /**
     * 
     * @throws IllegalArgumentException
     * @throws IllegalAccessException 
     */
    public void setLaureate() throws IllegalArgumentException, IllegalAccessException{
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f : fields){
            if (f.get(this)==null)
                f.set(this, "0");
        }
    }
    /**
     * @returns the share variable 
     */
    public String getShare(){
        return share;
    }
    /**
     * @returns the category variable 
     */
    public String getCategory(){
        return category;
    }
    /**
     * @returns the year variable 
     */
    public String getYear(){
        return year;
    }
    /**
     * @returns the firstname variable 
     */
    public String getFirstname(){
        return firstname;
    }
    /**
     * @returns the surname variable 
     */
    public String getSurname(){
        return surname;
    }
    /**
     * @returns the bornCountry variable 
     */
    public String getBornCountry(){
        return bornCountry;
    }
    /**
     * @returns the bornCity variable 
     */
    public String getBornCity(){
        return bornCity;
    }
   
    /**
     * @returns the diedcountry variable 
     */
    public String getDiedCountry(){
        return diedCountry;
    }
    /**
     * @returns the diedcity variable 
     */
    public String getDiedCity(){
        return diedCity;
    }
    /**
     * @returns the borndate variable 
     */
    public String getBornDate(){
        return bornDate;
    }
    /**
     * @returns the diedDate variable 
     */
    public String getDiedDate(){
        return diedDate;
    }
    /**
     * @returns the motivation variable 
     */
    public String getMotivation(){
        return motivation;
    }
    /**
     * @returns the gender variable 
     */
    public String getGender(){
        return gender;
    }
    /**
     * @returns the Affiliation variable 
     */
    public String getAffiliation(){
        return affiliation;
    }
}
