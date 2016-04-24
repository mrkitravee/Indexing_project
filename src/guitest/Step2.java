/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Kitravee
 */
public class Step2 {

    String line = " ";
    String fileFromStep1;
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    String nameMainSearch; // last file to store data

    Step2(String fileFromStep1) throws FileNotFoundException, IOException {
        this.fileFromStep1 = fileFromStep1;

    }

    void writeToMainSearching(String nameMainSearch) throws FileNotFoundException, IOException {
        this.nameMainSearch = nameMainSearch;
        BufferedReader fileReader = new BufferedReader(new FileReader(this.fileFromStep1));
        List sortingList = new ArrayList();
        while ((line = fileReader.readLine()) != null) {
            String linetrim = line.trim();
            String[] tokens = linetrim.split(COMMA_DELIMITER);

            if (tokens[0].length() >= 2) {
                Indexing indexing = new Indexing(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                sortingList.add(indexing);
            }
        }
        System.out.println("Sorting complete:");
        Collections.sort(sortingList, Indexing.wordComparator);
        Collections.sort(sortingList, Indexing.FrequencyComparator);

        FileWriter fileWriter = new FileWriter(this.nameMainSearch);
//                fileWriter.append(FILE_HEADER.toString());
//                fileWriter.append(NEW_LINE_SEPARATOR);
        try {
            int count = 1;
            for (int i = 0; i < sortingList.size(); i++) {
                Indexing y = new Indexing();
                y = (Indexing) sortingList.get(i);
                if (y.getWord().length() >= 2) {
                    // System.out.println(x.getWord());
                    fileWriter.append(Integer.toString(count));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(y.getWord());
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(Integer.toString(y.getFq() + 1));
                    fileWriter.append(COMMA_DELIMITER);
                    fileWriter.append(y.getDirectory());
                    fileWriter.append(NEW_LINE_SEPARATOR);
                    count++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        fileWriter.flush();
        fileWriter.close();
    }
    public void setNameMainSearch(String nameMainSearch){
        this.nameMainSearch = nameMainSearch;
    }
    public String getNameMainSearch(){
        return this.nameMainSearch;
    }
    public String getfileFromStep1(){
            return this.fileFromStep1;
    }

}
