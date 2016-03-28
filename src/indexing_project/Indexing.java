/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexing_project;

import java.util.Comparator;

/**
 *
 * @author Kitravee
 */
public class Indexing {
    String word;
    int frequency;
    String directory;
    //String textEx;
    Indexing(){
        
    }
    
    Indexing(String word,int frequency,String directory){
        this.word=word;
        this.frequency=frequency;
        this.directory=directory;
        //this.textEx = textEx;
    }

    
    public void setWord(String word){
        this.word=word;
    }
    public void setFq(int frequency){
        this.frequency=frequency;
    }
    public void setDirectory(){
        this.directory=directory;
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
   
    public String getDirectory(){
        return this.directory;
    }
//    public String getTextEx(){
//        return this.textEx;
//    }
 
public static Comparator<Indexing> wordComparator = new Comparator<Indexing>() {

    public int compare(Indexing s1, Indexing s2) {
	   String indexWord1 = s1.getWord().toUpperCase();
	   String indexWord2 = s2.getWord().toUpperCase();

	   //ascending order
	   return indexWord1.compareTo(indexWord2);

	   //descending order
	   //return indexWord2.compareTo(indexWord1);
    }};
public static Comparator<Indexing> FrequencyComparator = new Comparator<Indexing>() {

	public int compare(Indexing s1, Indexing s2) {

	   int rollno1 = s1.getFq();
	   int rollno2 = s2.getFq();

	   /*For ascending order*/
	   /*return rollno1-rollno2;*/

	   /*For descending order*/
	   return rollno2-rollno1;
   }};
public String toString() {
        return "[ word=" + this.word + ", Fq=" + this.frequency + ", directory=" + this.directory + "]";
    }
}

