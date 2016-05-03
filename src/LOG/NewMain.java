/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LOG;

import guitest.Indexing;
import static guitest.Step1.connectingWords;
import static guitest.Step1.frequencyMap;
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
public class NewMain {

    /**
     * @param args the command line arguments
     */
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String regexs = "\\W+|[0-9]+";
        String directory = "C:\\Users\\Kitravee\\Desktop\\personal\\ss.csv";
        String line;
        List sortingList = new ArrayList();
        BufferedReader reader = new BufferedReader(new FileReader(directory));

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
        reader.close();
        ArrayList<Integer> arr1 = new ArrayList<Integer>(frequencyMap.values());
        ArrayList<String> arr2 = new ArrayList<String>(frequencyMap.keySet());
        System.out.println(arr1);
        System.out.println(arr2);
        for (int i = 0; i < arr1.size(); i++) {
            Log L = new Log(arr2.get(i), arr1.get(i));
            sortingList.add(L);
        }
        System.out.println(sortingList);
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

        }
        fileWriter.flush();
        fileWriter.close();

    }
}
