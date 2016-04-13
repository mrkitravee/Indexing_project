/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

import static guitest.indexingAction.connectingWords;
import static guitest.indexingAction.frequencyMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.reflect.Array.set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class Step1 {

    public static HashSet<String> connectingWords;
    public static Map<String, Integer> frequencyMap;
    String regexs = "\\W+|[0-9]+";
    public static String[] stopwords = {"a","wa" ,"as", "able", "about", "above", 
        "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against",
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
        "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero", "'s"};

    static {
        Porter j = new Porter();
        
        connectingWords = new HashSet<>();
        connectingWords = new HashSet<String>(Arrays.asList(stopwords));

        for (char c = 'a'; c <= 'z'; c++) {
            connectingWords.add(String.valueOf(c));
        }
        for (char c = '0'; c <= '9'; c++) {
            connectingWords.add(String.valueOf(c));
        }
        
        for(int i=0 ; i<stopwords.length ; i++){
            connectingWords.add(j.stripAffixes(stopwords[i]));
        }
        
        frequencyMap = new HashMap<>();
    }
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "word,frequency,directory";

    String newDirectory, nameDirectoryTostoredata;
    String nameDirectoryTosearch;
    FileWriter fileWriter;
    List<String> SaveDirectory = new ArrayList<String>();

    Step1(String newDirectory) throws IOException {
        this.newDirectory = newDirectory;
        this.fileWriter = new FileWriter(this.newDirectory);
    }

    //write hash map to csv
    public void writeTocsv(String nameDirectoryTostoredata) throws IOException {
        this.nameDirectoryTostoredata = nameDirectoryTostoredata;
        Set set = this.frequencyMap.entrySet();
        try {
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {

                Map.Entry mentry = (Map.Entry) iterator.next();
                System.out.println((String) mentry.getKey());
                this.fileWriter.append((String) mentry.getKey());
                this.fileWriter.append(COMMA_DELIMITER);
                this.fileWriter.append(mentry.getValue().toString());
                this.fileWriter.append(COMMA_DELIMITER);
                this.fileWriter.append((String) this.nameDirectoryTostoredata);
                this.fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void hashToHashmap() throws IOException {
        int indexs = 0;
        FileSearch fileSearch = new FileSearch();//class for search all data in foder

        Porter x = new Porter();//class stemming
        BufferedReader reader = null;
        String line;
        fileSearch.searchDirectory(new File(this.nameDirectoryTosearch));
        for (String fileDirectorys : fileSearch.getResult()) {
            String directory = fileDirectorys;
            System.out.println("Direc : " + fileDirectorys);
            this.SaveDirectory.add(indexs, fileDirectorys);
            indexs++;//update List
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
                } else if (fileDirectorys.endsWith(".pdf")) {

                    PDFTextStripper stripper;
                    PDDocument doc = null;
                    doc = PDDocument.load(new File(fileDirectorys));

                    stripper = new PDFTextStripper();

                    stripper.setStartPage(1);
                    stripper.setEndPage(Integer.MAX_VALUE);
                    String x1 = stripper.getText(doc);

                    List<String> ans = Arrays.asList(stripper.getText(doc).split("\r\n"));

                    for (String lines : ans) {

                        String[] words = lines.split(regexs);
                        for (String wordx : words) {

                            if (wordx.length() > 2) {
                                String word = x.stripAffixes(wordx);
                                if (connectingWords.contains(word.toLowerCase())) {
                                    continue;
                                }
                                Integer value = frequencyMap.get(word.toLowerCase());

                                if (value != null) {

                                    frequencyMap.put(word.toLowerCase(), value + 1);
                                } else {

                                    frequencyMap.put(word.toLowerCase(), 0);
                                }
                            }
                        }
                    }
                    doc.close();

                } else if (fileDirectorys.endsWith(".docx")) {
                    try {
                        File file = new File(fileDirectorys);
                        FileInputStream fis = new FileInputStream(file.getAbsolutePath());
                        XWPFDocument document = new XWPFDocument(fis);
                        List<XWPFParagraph> paragraphs = document.getParagraphs();
                        System.out.println("Total no of paragraph " + paragraphs.size());
                        for (XWPFParagraph para : paragraphs) {
                            String[] words = para.getText().split(regexs);
                            for (String wordx : words) {
                                String word = x.stripAffixes(wordx);
                                if (this.connectingWords.contains(word.toLowerCase())) {
                                    continue;
                                }
                                Integer value = this.frequencyMap.get(word.toLowerCase());

                                if (value != null) {

                                    this.frequencyMap.put(word.toLowerCase(), value + 1);

                                } else {

                                    this.frequencyMap.put(word.toLowerCase(), 0);
                                }
                            }
                        }
                        fis.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else if (fileDirectorys.endsWith(".doc")) {
                    try {
                        boolean directoryOut = true;
                        File file = new File(fileDirectorys);
                        FileInputStream fis = new FileInputStream(file.getAbsolutePath());

                        HWPFDocument doc = new HWPFDocument(fis);
                        WordExtractor we = new WordExtractor(doc);

                        String[] words = we.getText().split(regexs);
                        
                        //System.out.println("Total no of paragraph "+paragraphs.length);
                        for (String wordx : words) {
                            String word = x.stripAffixes(wordx);
                            
                            if (this.connectingWords.contains(word.toLowerCase())) {
                                continue;
                            }
                            Integer value = this.frequencyMap.get(word.toLowerCase());

                            if (value != null) {

                                this.frequencyMap.put(word.toLowerCase(), value + 1);
                            } else {

                                this.frequencyMap.put(word.toLowerCase(), 0);
                            }
                        }

                        fis.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else if (fileDirectorys.endsWith(".xlsx")) {
                    File file = new File(fileDirectorys);
                    FileInputStream fis = new FileInputStream(new File(file.getAbsolutePath()));
                    XSSFWorkbook wb = new XSSFWorkbook(fis);
                    XSSFSheet sheet = wb.getSheetAt(0);
                    FormulaEvaluator forlulaEvauator = wb.getCreationHelper().createFormulaEvaluator();
                    boolean directoryOut = true;
                    for (Row row : sheet) {
                        for (Cell cell : row) {

                            //System.out.println(cell.getAddress());
                            switch (forlulaEvauator.evaluateInCell(cell).getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    String wordx = cell.getStringCellValue().toLowerCase();
                                    String word = x.stripAffixes(wordx);
                                    if (this.connectingWords.contains(word.toLowerCase())) {
                                        continue;
                                    }
                                    Integer value = this.frequencyMap.get(word.toLowerCase());

                                    if (value != null) {

                                        this.frequencyMap.put(word.toLowerCase(), value + 1);
                                    } else {

                                        this.frequencyMap.put(word.toLowerCase(), 0);
                                    }
                                    break;
                            }
                        }

                    }
                } else if (fileDirectorys.endsWith(".xls")) {
                    boolean directoryOut = true;
                    File file = new File(fileDirectorys);
                    FileInputStream fis = new FileInputStream(new File(file.getAbsolutePath()));
                    HSSFWorkbook wb = new HSSFWorkbook(fis);
                    HSSFSheet sheet = wb.getSheetAt(0);
                    FormulaEvaluator forlulaEvauator = wb.getCreationHelper().createFormulaEvaluator();
                    for (Row row : sheet) {
                        for (Cell cell : row) {

                            //System.out.println(cell.getAddress());
                            switch (forlulaEvauator.evaluateInCell(cell).getCellType()) {
                                case Cell.CELL_TYPE_STRING:
                                    switch (forlulaEvauator.evaluateInCell(cell).getCellType()) {
                                        case Cell.CELL_TYPE_STRING:
                                            String wordx = cell.getStringCellValue().toLowerCase();
                                            String word = x.stripAffixes(wordx);
                                            if (this.connectingWords.contains(word.toLowerCase())) {
                                                continue;
                                            }
                                            Integer value = this.frequencyMap.get(word.toLowerCase());

                                            if (value != null) {

                                                this.frequencyMap.put(word.toLowerCase(), value + 1);
                                            } else {

                                                this.frequencyMap.put(word.toLowerCase(), 0);
                                            }
                                            break;

                                    }
                            }
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
            writeTocsv(directory);
            frequencyMap.clear();

        }
    }

    public void close() throws IOException {
        this.fileWriter.flush();
        this.fileWriter.close();
    }

    public void setNameDirectoryTostoredata(String nameDirectoryTostoredata) {
        this.nameDirectoryTostoredata = nameDirectoryTostoredata;
    }

    public void setNameDirectoryTosearch(String nameDirectoryTosearch) {
        this.nameDirectoryTosearch = nameDirectoryTosearch;

    }

    public String getWritingDirectory() {
        return this.newDirectory;
    }

    public static String getParentName(File file) {
        if (file == null || file.isDirectory()) {
            return null;
        }
        String parent = file.getParent();
        parent = parent.substring(parent.lastIndexOf("\\") + 1, parent.length());
        return parent;
    }
    public List<String> getListDirectory(){
        return this.SaveDirectory;
    }
}
