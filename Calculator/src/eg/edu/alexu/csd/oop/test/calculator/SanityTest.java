package eg.edu.alexu.csd.oop.test.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import eg.edu.alexu.csd.oop.calculator.Calculator;
import eg.edu.alexu.csd.oop.test.TestRunner;

public class SanityTest {
       
    /**
     * This test should save then load to test the functionality itself. 
     */
    @org.junit.Test
    public void testSaveAndLoadWithoutHistory() {
    	    	
        Calculator instance = (Calculator) TestRunner.getImplementationInstanceForInterface(Calculator.class);
        String result = null;
        
        try {
            instance.save();
        } catch (Throwable e) {
            TestRunner.fail("Fail to save", e);
        }
        
        try {
            instance.input("3+4");
        } catch (Throwable e) {
            TestRunner.fail("Fail to set input '3+4'", e);
        }
        
        try {
            instance.load();
        } catch (Throwable e) {
            TestRunner.fail("Fail to load", e);
        }
        
        try {
            result = instance.current();
            assertNull("History should be empty", result);
        } catch (Throwable e) {
            TestRunner.fail("Fail to get current", e);
        }
    }
    
    /**
     * This test should insert 5 inputs then save, then load the program
     * and then check the history
     */
    @org.junit.Test
    public void testSaveAndLoadWithHistory() {
    	
        Calculator instance = (Calculator) TestRunner.getImplementationInstanceForInterface(Calculator.class);
        
        try {
            instance.input("1+2"); // 1
        } catch (Throwable e) {
            fail("Fail to set input '1+2");
        }
        try {
            instance.input("2+3"); // 2
        } catch (Throwable e) {
            fail("Fail to set input '2+3");
        }
        try {
            instance.input("3+4"); // 3
        } catch (Throwable e) {
            fail("Fail to set input '3+4");
        }
        try {
            instance.input("4+5"); // 4
        } catch (Throwable e) {
            fail("Fail to set input '4+5");
        }
        try {
            instance.input("5+6"); // 5
        } catch (Throwable e) {
            fail("Fail to set input '5+6");
        }
        try {
            instance.input("6+7"); // 6
        } catch (Throwable e) {
            fail("Fail to set input '6+7");
        }
        
        try {
            instance.save();
        } catch (Throwable e) {
            TestRunner.fail("Fail to save", e);
        }
        
        try {
            instance.input("7+8"); // 7
        } catch (Throwable e) {
            fail("Fail to set input '6+7");
        }
        try {
            instance.input("8+9"); // 8
        } catch (Throwable e) {
            fail("Fail to set input '6+7");
        }
        
        try {
            instance.load();
        } catch (Throwable e) {
            TestRunner.fail("Fail to load", e);
        }
        
        try {
            assertEquals("Current 1 Fails", "6+7", instance.current());
            assertEquals("Prev 1 Fails", "5+6", instance.prev());
            assertEquals("Prev 2 Fails", "4+5", instance.prev());
            assertEquals("Prev 3 Fails", "3+4", instance.prev());
            assertEquals("Prev 4 Fails", "2+3", instance.prev());
            assertNull("Prev 5 Fails", instance.prev());
            assertNull("Prev 6 Fails", instance.prev());
            assertEquals("Current 2 Fails", "2+3", instance.current());
            assertEquals("Next 1 Fails", "3+4", instance.next());
        } catch (Throwable e) {
            TestRunner.fail("History Fails", e);
        }
        
    }
    
    /**
     * This test should insert 3 inputs and then do prev, then save and load the program, 
     * and then print the results
     */
    @org.junit.Test
    public void testCurrentWithSaveAndLoad() {
    	
        Calculator instance = (Calculator) TestRunner.getImplementationInstanceForInterface(Calculator.class);
        
        try {
            instance.input("1+2"); // 1
        } catch (Throwable e) {
            fail("Fail to set input '1+2");
        }
        try {
            instance.input("2+3"); // 2
        } catch (Throwable e) {
            fail("Fail to set input '2+3");
        }
        try {
            instance.input("3+4"); // 3
        } catch (Throwable e) {
            fail("Fail to set input '3+4");
        }
        
        try {
            assertEquals("Current 1 Fails", "3+4", instance.current());
            assertEquals("Prev 1 Fails", "2+3", instance.prev());
            assertEquals("Next 1 Fails", "3+4", instance.next());
            assertNull("Next 2 Fails", instance.next());
            assertEquals("Prev 2 Fails", "2+3", instance.prev());
            assertEquals("Prev 3 Fails", "1+2", instance.prev());
            assertNull("Prev 4 Fails", instance.prev());
        } catch (Throwable e) {
            TestRunner.fail("History Fails", e);
        }
        
        try {
            instance.save();
        } catch (Throwable e) {
            TestRunner.fail("Fail to save", e);
        }
        
        try {
            assertEquals("Next 3 Fails", "2+3", instance.next());
            assertEquals("Current 2 Fails", "2+3", instance.current());
        } catch (Throwable e) {
            TestRunner.fail("History Fails", e);
        }
        
        try {
            instance.load();
        } catch (Throwable e) {
            TestRunner.fail("Fail to load", e);
        }
        
        try {
            assertEquals("Current 3 Fails", "1+2", instance.current());
        } catch (Throwable e) {
            TestRunner.fail("History Fails", e);
        }
    }
    
    /**
     * This test should test the float numbers operations
     */
    @org.junit.Test
    public void testFloatOperations() {
    	
        Calculator instance = (Calculator)TestRunner.getImplementationInstanceForInterface(Calculator.class);
        String result = null;

        try {
            instance.input("3.5+4.35");
        } catch (Throwable e) {
            TestRunner.fail("Fail to set input '3.5+4.35'", e);
        }
        try {
            result = instance.getResult();
            assertEquals("Addition doesn't work '3.5+4.35'", "7.85", result);
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '3.5+4.35'", e);
        }

        try {
            instance.input("3.5-4.35");
        } catch (Throwable e) {
            TestRunner.fail("Fail to set input '3.5-4.35'", e);
        }
        try {
            result = instance.getResult();
            assertEquals("Subtraction doesn't work '3.5-4.35'", "-0.8499999999999996", result);
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '3.5-4.35'", e);
        }

        try {
            instance.input("7.5/3.75");
        } catch (Throwable e) {
            TestRunner.fail("Fail to set input '7.5/3.75'", e);
        }
        try {
            result = instance.getResult();
            assertEquals("Division doesn't work '7.5/3.75'", "2.0", result);
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '7.5/3.75'", e);
        }

        try {
            instance.input("3.5*4.3");
        } catch (Throwable e) {
            TestRunner.fail("Fail to set input '3.5*4.3'", e);
        }
        try {
            result = instance.getResult();
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '3.5*4.3'", e);
        }
        assertEquals("Multiplication doesn't work '3.5*4.3'", "15.049999999999999", result);
    }
    
    /**
     * This test should make prev, next, current with an empty history
     */
    @org.junit.Test
    public void testNoInputHistory() {
    	
        Calculator instance = (Calculator)TestRunner.getImplementationInstanceForInterface(Calculator.class);
        
        try {
            assertNull("Current 1 Fails", instance.current());
            assertNull("Next 1 Fails", instance.next());
            assertNull("Next 1 Fails", instance.prev());
        } catch (Throwable e) {
            TestRunner.fail("History Fails", e);
        }
    }
    
    /**
     * This test should insert 3 operations then get the results for al of them,
     * and it should test boundaries null at far next and prev
     */
    @org.junit.Test
    public void testInputHistory() {
    	
        Calculator instance = (Calculator) TestRunner.getImplementationInstanceForInterface(Calculator.class);
        
        try {
            instance.input("1+2"); // 1
        } catch (Throwable e) {
            fail("Fail to set input '1+2");
        }
        try {
            instance.input("2+3"); // 2
        } catch (Throwable e) {
            fail("Fail to set input '2+3");
        }
        try {
            instance.input("3+4"); // 3
        } catch (Throwable e) {
            fail("Fail to set input '3+4");
        }
        
        try {
            assertEquals("Current 1 Fails", "3+4", instance.current());
            assertEquals("Prev 1 Fails", "2+3", instance.prev());
            assertEquals("Prev 2 Fails", "1+2", instance.prev());
            assertNull("Prev 3 Fails", instance.prev());
            assertEquals("Current 2 Fails", "1+2", instance.current());
            assertEquals("Next 1 Fails", "2+3", instance.next());
            assertEquals("Next 2 Fails", "3+4", instance.next());
            assertNull("Next 3 Fails", instance.next());
            assertEquals("Current 3 Fails", "3+4", instance.current());
        } catch (Throwable e) {
            TestRunner.fail("History Fails", e);
        }
    }

    /**
     * This test should check the result against history without current
     */
    @org.junit.Test
    public void testResultAndHistory() {
    	TestRunner.initaiteforInterface(Calculator.class);
        Calculator instance = (Calculator) TestRunner.getImplementationInstanceForInterface(Calculator.class);
        String result = null;
        
        try {
            instance.input("1+2"); // 1
        } catch (Throwable e) {
            fail("Fail to set input '1+2");
        }
        try {
            instance.input("2+3"); // 2
        } catch (Throwable e) {
            fail("Fail to set input '2+3");
        }
        try {
            instance.input("3+4"); // 3
        } catch (Throwable e) {
            fail("Fail to set input '3+4");
        }
        try {
            instance.input("4+5"); // 4
        } catch (Throwable e) {
            fail("Fail to set input '4+5");
        }
        try {
            instance.input("5+6"); // 5
        } catch (Throwable e) {
            fail("Fail to set input '5+6");
        }
        
        try {
            result = instance.getResult();
            assertEquals("Addition doesn't work '5+6'", "11.0", result);
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '5+6'", e);
        }
        
        try {
            instance.input("6+7"); // 6
        } catch (Throwable e) {
            fail("Fail to set input '6+7");
        }
        
        try {
            result = instance.getResult();
            assertEquals("Addition doesn't work '6+7'", "13.0", result);
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '6+7'", e);
        }
        
        try {
            assertEquals("Prev 1 Fails", "5+6", instance.prev());
            assertEquals("Prev 2 Fails", "4+5", instance.prev());
        } catch (Throwable e) {
            TestRunner.fail("History Fails", e);
        }
        
        try {
            result = instance.getResult();
            assertEquals("Addition doesn't work '4+5'", "9.0", result);
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '4+5'", e);
        }
        
        try {
            assertEquals("Prev 3 Fails", "3+4", instance.prev());
            assertEquals("Prev 4 Fails", "2+3", instance.prev());
            assertNull("Prev 5 Fails", instance.prev());
        } catch (Throwable e) {
            TestRunner.fail("History Fails", e);
        }
        
        try {
            result = instance.getResult();
            assertEquals("Addition doesn't work '2+3'", "5.0", result);
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '2+3'", e);
        }
    }
}