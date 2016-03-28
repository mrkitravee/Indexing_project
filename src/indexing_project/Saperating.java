/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexing_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

/**
 *
 * @author Kitravee
 */
public class Saperating {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    /**
     * @param args the command line arguments
     */
//        String s = "-I want to walk my dog";
//
//        String[] arr = s.split(" ");
//        System.out.println(arr.toString());
//        for ( String ss : arr) {
//            System.out.println(ss);
//        }

    public static void main(String[] args) throws IOException {
//
////        List<String> SaveResult = new ArrayList<String>();
//////อ่านข้อมูล คัดแย้งข้อมูลที่เหมือนเข้าลงใน ตาราง Hash เขียนลง CSV
//        Step1 writing1 = new Step1("C:\\Users\\Kitravee\\Desktop\\indexing\\storeState\\test1.csv");// ไฟล์ที่เก็บไว้ตอนแรกยังไม่เรียง
//        writing1.setNameDirectoryTosearch("C:\\Users\\Kitravee\\Desktop\\pro2");// โฟเดอร์ที่เก็บไฟล์ทั้งหมด
//        writing1.hashToHashmap();
//        writing1.close();
////        
////        
//////class sorting
//        Step2 writing2 = new Step2("C:\\Users\\Kitravee\\Desktop\\indexing\\storeState\\test1.csv"); //เอาไฟล์ที่เก็บไว้
//        writing2.writeToMainSearching("C:\\Users\\Kitravee\\Desktop\\indexing\\soutingState\\rrSort1.csv"); //ไฟล์ที่เรียงเรียบร้อย
////

        indexingAction act=new indexingAction();
        //act.removeIndexFile("C:\\Users\\Kitravee\\Documents\\NetBeansProjects\\GUItest\\pro2\\CollegeAlgebra-OP.pdf", "C:\\Users\\Kitravee\\Documents\\NetBeansProjects\\GUItest\\indexing\\storeState","C:\\Users\\Kitravee\\Documents\\NetBeansProjects\\GUItest\\indexing\\soutingState");
       //act.addIndexFile("C:\\Users\\Kitravee\\Desktop\\project2\\kitravee.txt","C:\\Users\\Kitravee\\Documents\\NetBeansProjects\\GUItest\\indexing\\storeState", "C:\\Users\\Kitravee\\Documents\\NetBeansProjects\\GUItest\\indexing\\soutingState");
        
    }
}
