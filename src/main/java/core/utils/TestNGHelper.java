package core.utils;

import core.managers.MyLogger;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class TestNGHelper {

    //Run testNG programmatically by Class name
    public static void runTestNGSuite(String className) {
        MyLogger.logSys("Running TestNG");
        List<XmlSuite> suites = new ArrayList<>();
        List<XmlClass> classes = new ArrayList<>();

        XmlSuite suite = new XmlSuite();
        suite.setName("AutomationSuite");

        XmlTest test = new XmlTest(suite);
        test.setName("MyTest");

        XmlClass class1 = new XmlClass(className);
        classes.add(class1);

        test.setXmlClasses(classes);

        suites.add(suite);

        TestNG testNG = new TestNG();
        testNG.setXmlSuites(suites);
        testNG.run();
    }
}
