package guitest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kitravee
 */
import au.com.bytecode.opencsv.CSVReader;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class Tutorial extends JPanel
{
     JTable jt;
 
     public Tutorial() throws FileNotFoundException, IOException
     {  
        //CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Kitravee\\Desktop\\indexing\\soutingState\\rrSort1.csv")); 
        //List myEntries = reader.readAll();
        //JTable table = new JTable(myEntries.toArray());
         
        // Read all
        CSVReader csvReader = new CSVReader(new FileReader(new File("C:\\Users\\Kitravee\\Desktop\\indexing\\soutingState\\rrSort1.csv")));
        List<String[]> list = csvReader.readAll();

        // Convert to 2D array
        String[][] dataArr = new String[list.size()][];
        dataArr = list.toArray(dataArr);
          
        String[] columns = {"Word", "frequency", "Directory"};
  
          String[][] data = {{"John", "18", "Male"},
                  {"Daisy", "19", "Female"},
                  {"Dave", "23", "Male"},
                  {"Jake", "30", "Male"}};
  
          jt = new JTable(dataArr, columns)
          {
               public boolean isCellEditable(int data, int columns)
               {
                   return false;
               }
   
               public Component prepareRenderer(TableCellRenderer r, int data, int columns)
               {
                   Component c = super.prepareRenderer(r, data, columns);
    
                   if (data % 2 == 0)
                       c.setBackground(Color.WHITE);
    
                   else
                       c.setBackground(Color.LIGHT_GRAY);
                    if(isCellSelected(data, columns)){
                       c.setBackground(Color.GREEN);
                    }
                   return c;
               }
         };
  
  
         jt.setPreferredScrollableViewportSize(new Dimension(450, 63));
         jt.setFillsViewportHeight(true);
  
         JScrollPane jps = new JScrollPane(jt);
         add(jps);
  
     }
 
     public static void main(String[] args) throws IOException
     {
         String Str = new String("Welcome to Tutorialspoint.com");

      System.out.print("Return Value :" );
      System.out.println(Str.substring(10) );

      System.out.print("Return Value :" );
      System.out.println(Str.substring(11, 15) );
      
     }
}
