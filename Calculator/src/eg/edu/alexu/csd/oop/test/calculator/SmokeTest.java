package eg.edu.alexu.csd.oop.test.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import eg.edu.alexu.csd.oop.calculator.Calculator;
import eg.edu.alexu.csd.oop.test.TestRunner;

public class SmokeTest {
    
  
    
    @org.junit.Test
    public void testOperation() {
        Calculator instance = (Calculator)TestRunner.getImplementationInstanceForInterface(Calculator.class);;
        String result = null;
        try {
            instance.input("3+4");
        } catch (Throwable e) {
            TestRunner.fail("Fail to set input '3+4'", e);
        }
        try {
            result = instance.getResult();
            assertEquals("Addition doesn't work '3+4'", "7.0", result);
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '3+4'", e);
        }


        try {
            instance.input("3-4");
        } catch (Throwable e) {
            TestRunner.fail("Fail to set input '3-4'", e);
        }
        try {
            result = instance.getResult();
            assertEquals("Subtraction doesn't work '3-4'", "-1.0", result);
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '3-4'", e);
        }

        try {
            instance.input("3/4");
        } catch (Throwable e) {
            TestRunner.fail("Fail to set input '3/4'", e);
        }
        try {
            result = instance.getResult();
            assertEquals("Division doesn't work '3/4'", "0.75", result);
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '3/4'", e);
        }

        try {
            instance.input("3*4");
        } catch (Throwable e) {
            TestRunner.fail("Fail to set input '3*4'", e);
        }
        try {
            result = instance.getResult();
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '3*4'", e);
        }
        assertEquals("Multiplication doesn't work '3*4'", "12.0", result);
        
        try {
            instance.input("-3*-4");
        } catch (Throwable e) {
            TestRunner.fail("Fail to set input '-3*-4'", e);
        }
        try {
            result = instance.getResult();
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '-3*-4'", e);
        }
        assertEquals("Multiplication doesn't work '-3*-4'", "12.0", result);
        
        try {
            instance.input("-3--4");
        } catch (Throwable e) {
            TestRunner.fail("Fail to set input '-3--4'", e);
        }
        try {
            result = instance.getResult();
        } catch (Throwable e) {
            TestRunner.fail("Fail to get result of '-3--4'", e);
        }
        assertEquals("Multiplication doesn't work '-3--4'", "1.0", result);
    }

    @org.junit.Test
    public void testHistory() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Calculator instance = (Calculator)TestRunner.getImplementationInstanceForInterface(Calculator.class);;
        try {
            instance.input("1+2");
        } catch (Throwable e) {
            fail("Fail to set input '1+2");
        }
        try {
            instance.input("2+3");
        } catch (Throwable e) {
            fail("Fail to set input '2+3");
        }
        try {
            instance.input("3+4");
        } catch (Throwable e) {
            fail("Fail to set input '3+4");
        }
        try {
            instance.input("4+5");
        } catch (Throwable e) {
            fail("Fail to set input '4+5");
        }
        try {
            instance.input("5+6");
        } catch (Throwable e) {
            fail("Fail to set input '5+6");
        }
        try {
            instance.input("6+7");
        } catch (Throwable e) {
            fail("Fail to set input '6+7");
        }
        try {
            assertEquals("Current 1 Fails", "6+7", instance.current());
            assertEquals("Prev 1 Fails", "5+6", instance.prev());
            assertEquals("Prev 2 Fails", "4+5", instance.prev());
            assertEquals("Prev 3 Fails", "3+4", instance.prev());
            assertEquals("Prev 4 Fails", "2+3", instance.prev());
            assertEquals("Prev 5 Fails", null, instance.prev());
            assertEquals("Prev 6 Fails", null, instance.prev());
            assertEquals("Current 2 Fails", "2+3", instance.current());
            assertEquals("Next 1 Fails", "3+4", instance.next());
        } catch (Throwable e) {
            TestRunner.fail("History Fails", e);
        }
    }
    
    @org.junit.Test
    public void testHistoryAndCurrent() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Calculator instance = (Calculator)TestRunner.getImplementationInstanceForInterface(Calculator.class);;
        try {
            instance.input("1+2");
        } catch (Throwable e) {
            fail("Fail to set input '1+2");
        }
        try {
            instance.input("2+3");
        } catch (Throwable e) {
            fail("Fail to set input '2+3");
        }
        try {
            instance.input("6+7");
        } catch (Throwable e) {
            fail("Fail to set input '6+7");
        }
        try {
            assertEquals("Current 1 Fails", "6+7", instance.current());
            assertEquals("Prev 1 Fails", "2+3", instance.prev());
            assertEquals("Prev 2 Fails", "1+2", instance.prev());
        } catch (Throwable e) {
            TestRunner.fail("History and Current fails Fails", e);
        }
        try {
            instance.input("20+30");
        } catch (Throwable e) {
            fail("Fail to set input '20+30");
        }
        try {
            assertEquals("Current 1 Fails", "20+30", instance.current());
            assertEquals("Prev 1 Fails", "6+7", instance.prev());
            assertEquals("Next 1 Fails", "20+30", instance.next());
            assertEquals("Next 2 Fails", null, instance.next());
        } catch (Throwable e) {
            TestRunner.fail("History and Current fails Fails", e);
        }
        
    }

}