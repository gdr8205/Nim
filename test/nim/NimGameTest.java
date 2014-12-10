/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nim;

import nim.Exceptions.*;  // import exceptions

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Garrett
 */
public class NimGameTest {
    
    public NimGameTest() {
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
     * Test of getRow method, of class NimGame.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        int r = 1;
        NimGame instance = new NimGame(new int[]{3,5,7} );
        int expResult = 5;
        int result = instance.getRow(r);
        assertEquals(expResult, result);
    }

    /**
     * Test of play method, of class NimGame.
     */
    @Test
    public void testPlayLegal() {
        System.out.println("playLegal");
        int r = 0;
        int s = 3;
        NimGame instance = new NimGame(new int[]{3,5,7});
        String expResult  = "# of rows: 3 [0] [5] [7] ";
        
        instance.play(r, s);
        
        String result = instance.toString();
        
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testPlayIllegal() {
        System.out.println("playIllegal");
        int r = 10;
        int s = 3;
        NimGame instance = new NimGame(new int[]{3,5,7});
        String expResult  = "# of rows: 3 [3] [5] [7] ";
        
        try {
            instance.play(r, s);
            fail("Row shouldn't exist.");
        } catch (NoSuchRowException ex){
            String result = instance.toString();
            assertEquals(expResult, result);
        }
        
        
        
    }

    
    
    
    
//    /**
//     * Test of isOver method, of class NimGame.
//     */
//    @Test
//    public void testIsOver() {
//        System.out.println("isOver");
//        NimGame instance = null;
//        boolean expResult = false;
//        boolean result = instance.isOver();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of AIMove method, of class NimGame.
//     */
//    @Test
//    public void testAIMove() {
//        System.out.println("AIMove");
//        NimGame instance = null;
//        instance.AIMove();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class NimGame.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        NimGame instance = null;
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
