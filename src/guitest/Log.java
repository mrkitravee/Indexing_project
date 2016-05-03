/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

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
    public void updateLog() throws IOException{
        BufferedReader reader = null;
        try {
            String regexs = "\\W+|[0-9]+";
            String directory = "C:\\Users\\Kitravee\\Desktop\\personal\\ss.csv";
            String line;
            List sortingList = new ArrayList();
            reader = new BufferedReader(new FileReader(directory));
            try {
                while ((line = reader.readLine()) != null) {
                    if (line.length() < 2) {
                        continue;
                    }
                    String[] array = line.split(regexs);
                    String word = array[0].toLowerCase();
                    
                    Integer value = frequencyMap.get(array[0].toLowerCase());
                    if (value != null) {
                        
                        frequencyMap.put(word.toLowerCase(), value + 1);
                    } else {
                        frequencyMap.put(word.toLowerCase(), 0);
                    }
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            }
reader.close();
            ArrayList<Integer> arr1 = new ArrayList<Integer>(frequencyMap.values());
            ArrayList<String> arr2 = new ArrayList<String>(frequencyMap.keySet());
            System.out.println(arr1);
            System.out.println(arr2);
            for (int i = 0; i < arr1.size(); i++) {
                Log L = new Log(arr2.get(i), arr1.get(i));
                sortingList.add(L);
            }   System.out.println(sortingList);
            Collections.sort(sortingList, Log.wordComparator);
            Collections.sort(sortingList, Log.FrequencyComparator);
            System.out.println(sortingList);
            FileWriter fileWriter = new FileWriter("C:\\Users\\Kitravee\\Desktop\\personal\\finish.csv");
            for (int i = 0; i < sortingList.size(); i++) {
                
                Log y = new Log();
                y = (Log) sortingList.get(i);
                // System.out.println(x.getWord());
                
                fileWriter.append(y.getWord());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(Integer.toString(y.getFq() + 1));
                fileWriter.append(NEW_LINE_SEPARATOR);
                
            }   fileWriter.flush();
            fileWriter.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
 
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