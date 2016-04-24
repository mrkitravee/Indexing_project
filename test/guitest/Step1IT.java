/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
public class Step1IT {
    
    public Step1IT() {
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
     * Test of getNameDirectoryTostoredata method, of class Step1.
     */
    @Test
    public void testGetNameDirectoryTostoredata() throws IOException {
        System.out.println("getNameDirectoryTostoredata");
        Step1 instance = new Step1("test11");
        instance.setNameDirectoryTostoredata("test");
        String expResult = "test";
        String result = instance.getNameDirectoryTostoredata();
        assertEquals(expResult, result);
   
    }

    /**
     * Test of getNameDirectoryTosearch method, of class Step1.
     */
    @Test
    public void testGetNameDirectoryTosearch() throws IOException {
        System.out.println("getNameDirectoryTosearch");
        Step1 instance = new Step1("test11");
        instance.setNameDirectoryTosearch("test");
        String expResult = "test";
        String result = instance.getNameDirectoryTosearch();
        assertEquals(expResult, result);

    }

}
