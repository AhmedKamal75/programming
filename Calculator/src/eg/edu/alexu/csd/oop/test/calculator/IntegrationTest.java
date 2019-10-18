package eg.edu.alexu.csd.oop.test.calculator;

import java.util.List;

import org.junit.Assert;

import eg.edu.alexu.csd.oop.calculator.Calculator;
import eg.edu.alexu.csd.oop.test.ReflectionHelper;

public class IntegrationTest {
    
    private final Class<?> interfaceToTest = Calculator.class ;
    
    
    @org.junit.Test
    public void testCreation() {	
    	List<Class<?>> candidateClasses = ReflectionHelper.findClassesImpmenenting(interfaceToTest, interfaceToTest.getPackage());
  
    	Assert.assertNotNull("Failed to create instance using interfcae '" + interfaceToTest.getName() + "' !", candidateClasses);
    	Assert.assertEquals("You have more than one public implementation of the interface",1,candidateClasses.size());
    	
    
    }

}