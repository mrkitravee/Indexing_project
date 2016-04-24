/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

import java.io.IOException;
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
public class Step2IT {
    
    public Step2IT() {
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
     * Test of writeToMainSearching method, of class Step2.
     */
    
    @Test
    public void setAndgetNameMainSearch() throws IOException {
        String name = "test";
        Step2 x = new Step2("xx");
        x.setNameMainSearch(name);
        String text1 = x.getfileFromStep1();
        String text2 = x.getNameMainSearch();        
        assertEquals(text1, "xx");
        assertEquals(text2, "test");
    }
}
