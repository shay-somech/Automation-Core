package core.utils;

import core.managers.MyLogger;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class TestNGHelper {

    public static void runTestNGSuiteByClass(String className) {
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

    public static void runTestNGSuiteByPackage(String packageName) {
        TestNG testng = new TestNG();
        testng.setXmlSuites(getSuite(packageName));
        testng.run();
    }

    private static List<XmlSuite> getSuite(String packageName) {
        List<XmlSuite> suites = new ArrayList<>();
        XmlSuite eachSuite = new XmlSuite();
        eachSuite.setName("My Suite");
        eachSuite.setTests(getTest(eachSuite, packageName));
        MyLogger.logSys(eachSuite.toXml());
        suites.add(eachSuite);
        return suites;
    }

    private static List<XmlTest> getTest(XmlSuite suite, String packageName) {
        List<XmlTest> tests = new ArrayList<>();
        XmlTest eachTest = new XmlTest();
        tests.add(eachTest);
        eachTest.setName("My test");
        eachTest.setPackages(getPackages(packageName));
        eachTest.setSuite(suite);
        return tests;
    }

    private static List<XmlPackage> getPackages(String packageName) {
        List<XmlPackage> allPackages = new ArrayList<>();
        XmlPackage eachPackage = new XmlPackage();
        eachPackage.setName(packageName);
        allPackages.add(eachPackage);
        return allPackages;
    }
}
