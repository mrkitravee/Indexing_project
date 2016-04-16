/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kitravee
 */
public class IndexingIT {
    
    public IndexingIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setWord method, of class Indexing.
     */
    @Test
    public void testSetWord() {
        System.out.println("setWord");
        String word = "test";
        Indexing instance = new Indexing();
        instance.setWord(word);
        
    }

    /**
     * Test of setFq method, of class Indexing.
     */
    @Test
    public void testSetFq() {
        System.out.println("setFq");
        int frequency = 0;
        Indexing instance = new Indexing();
        instance.setFq(frequency);
       
    }

    /**
     * Test of setDirectory method, of class Indexing.
     */
    @Test
    public void testSetDirectory() {
        System.out.println("setDirectory");
        Indexing instance = new Indexing();
        instance.setDirectory();
        
    }

    /**
     * Test of getWord method, of class Indexing.
     */
    @Test
    public void testGetWord() {
        System.out.println("getWord");
        Indexing instance = new Indexing();
        String expResult = "test";
        instance.setWord(expResult);
        String result = instance.getWord();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of getFq method, of class Indexing.
     */
    @Test
    public void testGetFq() {
        System.out.println("getFq");
        Indexing instance = new Indexing();
        int expResult = 0;
        int result = instance.getFq();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getDirectory method, of class Indexing.
     */
    @Test
    public void testGetDirectory() {
        System.out.println("getDirectory");
        Indexing instance = new Indexing();
        String expResult = null;
        String result = instance.getDirectory();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of toString method, of class Indexing.
     */
  
    
}
