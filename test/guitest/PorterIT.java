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
public class PorterIT {
    
    public PorterIT() {
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
     * Test of stripAffixes method, of class Porter.
     */
    @Test
    public void testStripAffixes1() {
        System.out.println("stripAffixes");
        String str = "playing";
        Porter instance = new Porter();
        String expResult = "play";
        String result = instance.stripAffixes(str);
        assertEquals(expResult, result);
    }
    @Test
    public void testStripAffixes2() {
        System.out.println("stripAffixes");
        String str = "played";
        Porter instance = new Porter();
        String expResult = "play";
        String result = instance.stripAffixes(str);
        assertEquals(expResult, result);
        
    }
    public void testStripAffixes3() {
        System.out.println("stripAffixes");
        String str = "player";
        Porter instance = new Porter();
        String expResult = "play";
        String result = instance.stripAffixes(str);
        assertEquals(expResult, result);
        
    }
    
}
