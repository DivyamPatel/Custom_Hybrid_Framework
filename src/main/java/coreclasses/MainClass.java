package coreclasses;

import java.util.ArrayList;
import java.util.List;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class MainClass {

	public static void main(String[] args) {

		XmlSuite suite = new XmlSuite(); 
		suite.setName("Suite"); 
		suite.addListener("coreclasses.MyTestListener"); 
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("runners.TestRunner1"));
		XmlTest test = new XmlTest(suite); 
		test.setName("Test"); 
		test.setXmlClasses(classes); 	
		System.out.println(test.getClasses()); 
		List<XmlSuite> suites = new ArrayList<XmlSuite>(); 
		suites.add(suite); 
		TestNG testNG = new TestNG(); 
		testNG.setXmlSuites(suites); 
		System.out.println(suite.getSuiteFiles()); 
		TestListenerAdapter adp = new TestListenerAdapter(); 
		testNG.addListener(adp); 
		testNG.run();
	}
}