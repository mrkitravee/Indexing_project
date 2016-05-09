/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Kitravee
 */
public class NewClass {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("D:\\Myproject\\Index\\main\\rrSort1.csv"));
        String line;
        String word = "davidson".toLowerCase();
        String COMMA_DELIMITER=",";
        int count = 0;
        long startTime = System.currentTimeMillis();
        
        while ((line = fileReader.readLine()) != null) {
            String[] tokens = line.split(COMMA_DELIMITER);
            if(tokens[1].equals(word)){
                count ++;
                System.out.println("directory : "+tokens[3]);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(count + " document found");
        System.out.println("Time "+(endTime - startTime) +" milisec");
        System.out.println("-----------");
    }
}
