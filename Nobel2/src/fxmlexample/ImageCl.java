/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxmlexample;

import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Siham
 */
public class ImageCl {
    
    public String year;
    public String name;
    public String category;
    public String Beginning;
    
    ImageCl(String c, String y, String n){
        this.Beginning = "https://www.nobelprize.org//nobel_prizes/";
        this.year = y;
        this.category = c;
        this.name = n.toLowerCase();
    }
    /*
        Found that there are some names of organizations that have
        abbreviations to their names. If there is a () in the name
        then the url for their organiztions image goes by the abbreiviation.
    
        therefore added the extra if statment here.
    */
    public String getURL(){
        String url;
        if(this.name.contains("(")){
            int start = this.name.indexOf("(");
            int end = this.name.indexOf(")");
            String temp = this.name.substring(start+1, end);
            this.name = temp.toLowerCase();
            System.out.println(this.name);
        }
        
        url = this.Beginning  +this.category +
                "/laureates/" + this.year + "/" + this.name + "_postcard.jpg";
        
       
        return url;
    }
    
    public void getLaureateInfo(){
        
    
    }
}
