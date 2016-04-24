/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kitravee
 */

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileWriter;
import java.io.IOException;
import static java.lang.reflect.Array.set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Kitravee
 */
public class Step1oneFileCase {

    Map<String, Integer> frequencyMap;
    String newDirectory, directory;
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "word,frequency,directory";
    FileWriter fileWriter;

    Step1oneFileCase(String newDirectory) throws IOException {
        this.newDirectory = newDirectory;
        this.fileWriter = new FileWriter(this.newDirectory,true);
        //this.fileWriter.append(FILE_HEADER.toString());
        //this.fileWriter.append(NEW_LINE_SEPARATOR);
    }
    //write to store state csv
    public void write(Map<String, Integer> frequencyMap) throws IOException {

        //String fileName = "C:\\Users\\Kitravee\\Desktop\\project1\\rr"+1+ ".csv";
        this.frequencyMap = new HashMap<>();
        Set set = frequencyMap.entrySet();
        try {
            Iterator iterator = set.iterator();
            
            while (iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry) iterator.next();
//  2         Searching searching = new Searching(mentry.getKey().toString(),(Integer)mentry.getValue(),directory.toString());
//  2         searchh.add(searching);
                
                this.fileWriter.append((String) mentry.getKey());
                this.fileWriter.append(COMMA_DELIMITER);
                this.fileWriter.append(mentry.getValue().toString());
                this.fileWriter.append(COMMA_DELIMITER);
                this.fileWriter.append((String) this.directory);
                this.fileWriter.append(NEW_LINE_SEPARATOR);
                
                //System.out.print("key is: "+ mentry.getKey() + " & Value is: " + mentry.getValue() +"D:"+directory);
                //System.out.println(mentry.getValue());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void close() throws IOException {
        this.fileWriter.flush();
        this.fileWriter.close();
    }


    public void setDirectory(String directory) {
        this.directory = directory;

    }

    public String getWritingDirectory() {
        return this.newDirectory;
    }
    public void setWritingDirectory(String newDirectory) {
        this.newDirectory = newDirectory;
    }
}


