/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOG;

import guitest.*;
import static guitest.Step1.frequencyMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kitravee
 */


public class Log {
    String word;
    int frequency;
    String NEW_LINE_SEPARATOR = "\n";
    String COMMA_DELIMITER = ",";
    //String textEx;
    Log(){
        
    }
    
    Log(String word,int frequency){
        this.word=word;
        this.frequency=frequency;
        //this.textEx = textEx;
    }

   

    
    public void setWord(String word){
        this.word=word;
    }
    public void setFq(int frequency){
        this.frequency=frequency;
    }
    
   
//    public void setTextEx(){
//        this.textEx=textEx;
//    }
     public String getWord(){
        return this.word;
    }
    public int getFq(){
        return this.frequency;
    }
   
  
//    public String getTextEx(){
//        return this.textEx;
//    }
 
public static Comparator<Log> wordComparator = new Comparator<Log>() {

    public int compare(Log s1, Log s2) {
	   String indexWord1 = s1.getWord().toUpperCase();
	   String indexWord2 = s2.getWord().toUpperCase();

	   //ascending order
	   return indexWord1.compareTo(indexWord2);

	   //descending order
	   //return indexWord2.compareTo(indexWord1);
    }};
public static Comparator<Log> FrequencyComparator = new Comparator<Log>() {

	public int compare(Log s1, Log s2) {

	   int rollno1 = s1.getFq();
	   int rollno2 = s2.getFq();

	   /*For ascending order*/
	   /*return rollno1-rollno2;*/

	   /*For descending order*/
	   return rollno2-rollno1;
   }};
public String toString() {
        return "[ word=" + this.word + ", Fq=" + this.frequency  + "]";
    }
}