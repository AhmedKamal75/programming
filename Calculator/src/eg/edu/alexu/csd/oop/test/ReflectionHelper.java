package eg.edu.alexu.csd.oop.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import eg.edu.alexu.csd.oop.calculator.Calculator;

public class ReflectionHelper {

    public static List<Class<?>> findClassesImpmenenting(final Class<?> interfaceClass, final Package fromPackage) {

        if (interfaceClass == null) {
            System.out.println("Unknown subclass.");
            return null;
        }

        if (fromPackage == null) {
            System.out.println("Unknown package.");
            return null;
        }

        final List<Class<?>> rVal = new ArrayList<Class<?>>();
        try {
            final Class<?>[] targets = getAllClassesFromPackage(fromPackage.getName());
            if (targets != null) {
                for (Class<?> aTarget : targets) {
                    if (aTarget == null) {
                        continue;
                    }
                    else if (aTarget.equals(interfaceClass)) {
                       // System.out.println("Found the interface definition.");
                        continue;
                    }
                    else if (!interfaceClass.isAssignableFrom(aTarget)) {
                        //System.out.println("Class '" + aTarget.getName() + "' is not a " + interfaceClass.getName());
                        continue;
                    }
                    else {
                        rVal.add(aTarget);
                    }
                }
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error reading package name.");
            //System.out.printStackTrace(e, System.out.LOW_LEVEL);
        }
        catch (IOException e) {
            System.out.println("Error reading classes in package.");
            //System.out.printStackTrace(e, System.out.LOW_LEVEL);
        }

        return rVal;
    }

    /**
     * Load all classes from a package.
     * 
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class<?>[] getAllClassesFromPackage(final String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Find file in package.
     * 
     * @param directory
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     */
    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            }
            else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return filterConcerteClasses(classes);
    }
    
    private static List<Class<?>> filterConcerteClasses(List<Class<?>> classes){
    	
    	List<Class<?>> filteredClasses = null ;
    	
    	for (Class<?> fetchedClass : classes){
    		Integer modifiers = fetchedClass.getModifiers();
    		
    		if (!Modifier.isInterface(modifiers) && !Modifier.isAbstract(modifiers) && Modifier.isPublic(modifiers)){
    			if ( filteredClasses == null ){
    				filteredClasses = new ArrayList<Class<?>>();
    			}
    			
    			filteredClasses.add(fetchedClass);
    		}
    	}
    
    	return filteredClasses;
    }
    
}