/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

/**
 *
 * @author Kitravee
 */
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import static guitest.Step1.frequencyMap;
public class indexingAction {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Kitravee
 */

    public static HashSet<String> connectingWords;
    public static Map<String,Integer> frequencyMap;
    public static String[] stopwords = {"a", "as", "able", "about", "above", "according", 
        "accordingly", "across", "actually", "after", "afterwards", "again", "against",
        "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", 
        "although", "always", "am", "among", "amongst", "an", "and", "another", "any", 
        "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", 
        "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", 
        "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", 
        "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", 
        "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", 
        "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", 
        "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", 
        "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", 
        "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", 
        "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", 
        "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except",
        "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", 
        "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", 
        "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", 
        "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein",
        "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however",
        "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", 
        "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its",
        "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", 
        "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking",
        "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", 
        "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", 
        "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", 
        "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often",
        "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", 
        "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed",
        "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really",
        "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", 
        "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves",
        "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so",
        "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", 
        "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", 
        "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", 
        "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", 
        "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", 
        "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly",
        "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", 
        "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was",
        "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", 
        "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon",
        "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will",
        "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", 
        "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero" ,"'s",""};
    static  {
        
        connectingWords = new HashSet<>();
        connectingWords = new HashSet<String>(Arrays.asList(stopwords));
       
        for (char c = 'a'; c <= 'z'; c++){
            connectingWords.add (String.valueOf (c));
        }
        for (char c = '0'; c <= '9'; c++){
            connectingWords.add (String.valueOf (c));
        }
       
        
        
        frequencyMap = new HashMap<>();
    }
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "word,frequency,directory";

    String word ;
    String text="" ;
    
    indexingAction() {
       
    }
        
        public String getText(){
            return this.text;
        }
        public void setWord(String word){
            this.word = word;
        }
        public void clearText(){
            this.text = "";
        }
        
        
        public void addIndexFile(String newFile,String oldStoreFile,String mainFile,int numFile) throws IOException{
            Porter x=new Porter();
            String regexs = "\\W+|[0-9]+";
            int test = 1;
            BufferedReader reader = null;
            String line;
            List<String> SaveResult = new ArrayList<String>();
            Step1oneFileCase writing1 = new Step1oneFileCase(oldStoreFile+"/test"+numFile+".csv");//นำมาใส่ hash table

                String fileDirectorys = newFile;
                String directory = fileDirectorys;
                try {
                    if(fileDirectorys.endsWith(".txt")||fileDirectorys.endsWith(".csv")){
                        reader = new BufferedReader(new FileReader(directory));
                        while ((line = reader.readLine()) != null) {

                            String linetrim = line.trim();
                            //System.out.println("line : "+linetrim);
                            String[] words = linetrim.split(regexs);

                            for (String wordx : words) {
                                //System.out.println("word : "+word);
                                if(wordx.length()>2){
                                    String word = x.stripAffixes(wordx);
                                    if(connectingWords.contains(word.toLowerCase())) {
                                        continue;
                                    }
                                    Integer value = frequencyMap.get(word.toLowerCase());

                                    if(value != null) {

                                        frequencyMap.put(word.toLowerCase(),value+1);
                                    } else {

                                        frequencyMap.put(word.toLowerCase(),0);
                                    }
                                }
                            }
                        }
                    reader.close();
                }else if(fileDirectorys.endsWith(".pdf")){

                    PDFTextStripper stripper;
                    PDDocument doc = null;
                    doc = PDDocument.load(new File(fileDirectorys));

                    stripper = new PDFTextStripper();

                    stripper.setStartPage( 1 );
                    stripper.setEndPage( Integer.MAX_VALUE );
                    String x1= stripper.getText(doc);


                    List<String> ans= Arrays.asList(stripper.getText(doc).split("\r\n"));

                    for (String lines : ans){

                        String[] words = lines.split(regexs);
                        for (String wordx : words){

                            if(wordx.length()>2){
                                String word = x.stripAffixes(wordx);
                                if(connectingWords.contains(word.toLowerCase())) {
                                    continue;
                                }
                                Integer value = frequencyMap.get(word.toLowerCase());

                                if(value != null) {

                                    frequencyMap.put(word.toLowerCase(),value+1);
                                } else {

                                    frequencyMap.put(word.toLowerCase(),0);
                                }
                            }
                        }
                    }doc.close();

                }else if(fileDirectorys.endsWith(".docx")){
                    try {
                                    File file = new File(fileDirectorys);
                                    FileInputStream fis = new FileInputStream(file.getAbsolutePath());

                                    XWPFDocument document = new XWPFDocument(fis);

                                    List<XWPFParagraph> paragraphs = document.getParagraphs();

                                    System.out.println("Total no of paragraph "+paragraphs.size());
                                    for (XWPFParagraph para : paragraphs) {
                                        String[] words = para.getText().split(regexs);
                                        for (String word : words) {
                                            
                                            if(connectingWords.contains(word.toLowerCase())) {
                                                continue;
                                            }
                                            Integer value = frequencyMap.get(word.toLowerCase());

                                            if(value != null) {

                                                frequencyMap.put(word.toLowerCase(),value+1);
                                            } else {

                                                frequencyMap.put(word.toLowerCase(),0);
                                            }
                                        }
                                    }fis.close();
                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }
                }else if(fileDirectorys.endsWith(".doc")){
                    try {   boolean directoryOut=true;
                                        File file = new File(fileDirectorys);
                                        FileInputStream fis = new FileInputStream(file.getAbsolutePath());

                                        HWPFDocument doc = new HWPFDocument(fis);
                                        WordExtractor we = new WordExtractor(doc);

                                        String[] words = we.getText().split(regexs);

                                        //System.out.println("Total no of paragraph "+paragraphs.length);

                                            for (String wordx : words) {
                                            //String word = x.stripAffixes(wordx);  ติดบัค ติดไว้ก่อนนนน!!!!!
                                            if(connectingWords.contains(word.toLowerCase())) {
                                                continue;
                                            }
                                            Integer value = frequencyMap.get(word.toLowerCase());

                                            if(value != null) {

                                                frequencyMap.put(word.toLowerCase(),value+1);
                                            } else {

                                                frequencyMap.put(word.toLowerCase(),0);
                                            }
                                        }



                                        fis.close();
                                }catch (Exception e) {
                                        System.out.println(e);
                                }
                }else if(fileDirectorys.endsWith(".xlsx")){
                                File file = new File(fileDirectorys);
                                FileInputStream fis = new FileInputStream(new File(file.getAbsolutePath()));
                                XSSFWorkbook wb = new XSSFWorkbook(fis) ;
                                XSSFSheet sheet = wb.getSheetAt(0);
                                FormulaEvaluator forlulaEvauator = wb.getCreationHelper().createFormulaEvaluator();
                                boolean directoryOut=true;
                                for(Row row : sheet){
                                    for(Cell cell : row){


                                        //System.out.println(cell.getAddress());
                                        switch(forlulaEvauator.evaluateInCell(cell).getCellType())
                                        {
                                            case Cell.CELL_TYPE_STRING:
                                                String wordx = cell.getStringCellValue().toLowerCase();
                                                String word = x.stripAffixes(wordx);
                                                if(connectingWords.contains(word.toLowerCase())) {
                                                continue;
                                                }
                                                Integer value = frequencyMap.get(word.toLowerCase());

                                                if(value != null) {

                                                    frequencyMap.put(word.toLowerCase(),value+1);
                                                } else {

                                                    frequencyMap.put(word.toLowerCase(),0);
                                                }
                                                break;

                                        }
                                    }
                                    System.out.println();
                                }
                            }else if(fileDirectorys.endsWith(".xls")){
                                boolean directoryOut=true;
                                File file = new File(fileDirectorys);
                                FileInputStream fis = new FileInputStream(new File(file.getAbsolutePath()));
                                HSSFWorkbook wb = new HSSFWorkbook(fis) ;
                                HSSFSheet sheet = wb.getSheetAt(0);
                                FormulaEvaluator forlulaEvauator = wb.getCreationHelper().createFormulaEvaluator();
                                for(Row row : sheet){
                                    for(Cell cell : row){

                                                switch(forlulaEvauator.evaluateInCell(cell).getCellType())
                                                    {
                                                        case Cell.CELL_TYPE_STRING:
                                                            String wordx = cell.getStringCellValue().toLowerCase();

                                                            String word = x.stripAffixes(wordx);
                                                            if(connectingWords.contains(word.toLowerCase())) {
                                                            continue;
                                                            }
                                                            Integer value = frequencyMap.get(word.toLowerCase());

                                                            if(value != null) {

                                                                frequencyMap.put(word.toLowerCase(),value+1);
                                                            } else {

                                                                frequencyMap.put(word.toLowerCase(),0);
                                                            }
                                                            break;

                                                    }
                                        }

                                    System.out.println();
                                }
                            }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {


                }

                System.out.println("xxx");
                writing1.setDirectory(directory);
                writing1.write(frequencyMap);

                frequencyMap.clear(); 
                writing1.close();


               


            //Sorting
            
            
            List<String> SaveResult2 = new ArrayList<String>();

               

                    List sortingList = new ArrayList();
                    BufferedReader fileReader = new BufferedReader(new FileReader(oldStoreFile+"/test"+numFile+".csv"));
    //                String headerLine = fileReader.readLine();

                    while ((line = fileReader.readLine()) != null) {
                           String linetrim = line.trim();
                           String[] tokens = linetrim.split(COMMA_DELIMITER);
                           Indexing indexing = new Indexing(tokens[0],Integer.parseInt(tokens[1]), tokens[2]);
                           sortingList.add(indexing);
                    }
                    System.out.println("Sorting:");
                    Collections.sort(sortingList, Indexing.wordComparator);
                    Collections.sort(sortingList, Indexing.FrequencyComparator);
                    

                    FileWriter fileWriter = new FileWriter(mainFile+"/rrSort"+numFile+".csv");
    //                fileWriter.append(FILE_HEADER.toString());
    //                fileWriter.append(NEW_LINE_SEPARATOR);
                   try {
                    int count = 1;
                    for (int i=0 ; i<sortingList.size();i++) {
                        Indexing y = new Indexing();
                        y=(Indexing) sortingList.get(i);
                            fileWriter.append(Integer.toString(count));
                            fileWriter.append(COMMA_DELIMITER);
                            fileWriter.append(y.getWord());
                            fileWriter.append(COMMA_DELIMITER);
                            fileWriter.append(Integer.toString(y.getFq()+1));
                            fileWriter.append(COMMA_DELIMITER);
                            fileWriter.append(y.getDirectory());
                            fileWriter.append(NEW_LINE_SEPARATOR);
                        count++;
                    }
                    }catch (Exception e) {
                    System.out.println(e);
                        }
                        fileWriter.flush();
                        fileWriter.close();
                    }
        public void removeIndexFile(String fileToremove,String oldStoreFile,String mainFile,int numFile) throws FileNotFoundException, IOException{
            File inputFile = new File(mainFile+"/rrSort"+numFile+".csv");
            File tempFile = new File(mainFile+"/rrSort"+(numFile+1)+".csv");
            
            //remove data in main file
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = fileToremove;//ชื่อของ directory ที่ต้องการ remove
            String currentLine;
            int count =1;
            while((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                String[] array = trimmedLine.split(",");
                if(array[3].equals(lineToRemove)) continue;
                writer.write(Integer.toString(count)+currentLine.substring(currentLine.indexOf(','))+ System.getProperty("line.separator"));
//แบบไม่ update
//                writer.write(currentLine + System.getProperty("line.separator"));
//                System.out.println(currentLine + System.getProperty("line.separator"));
                count++;
            }
            writer.close(); 
            reader.close(); 
            
            
            //remove data in store file
            
            inputFile = new File(oldStoreFile+"/test"+numFile+".csv");
            tempFile = new File(oldStoreFile+"/test"+(numFile+1)+".csv");
            
            
            reader = new BufferedReader(new FileReader(inputFile));
            writer =new BufferedWriter(new FileWriter(tempFile));
            while((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                String[] array = trimmedLine.split(",");
                if(array[2].equals(lineToRemove)) continue;
                
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close(); 
            reader.close(); 
           
            
        }
        
        
}




