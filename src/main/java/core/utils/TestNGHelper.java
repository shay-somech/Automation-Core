package core.utils;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestNGHelper {

    private final String suiteName;
    private final String testName;
    private final String deviceId;
    private final String deviceName;
    private final Class testClass;
    private final int suiteVerbose;
    private final int suiteThreadCount;
    private final ParallelMode parallelMode;


    public TestNGHelper(String suiteName, String testName, String deviceId, String deviceName, Class testClass, int suiteVerbose, int suiteThreadCount, ParallelMode parallelMode) {
        this.suiteName = suiteName;
        this.testName = testName;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.testClass = testClass;
        this.suiteVerbose = suiteVerbose;
        this.suiteThreadCount = suiteThreadCount;
        this.parallelMode = parallelMode;
    }

    public void createTestNGXml() {
        /** Configure Suite */
        XmlSuite xmlSuite = new XmlSuite();
        xmlSuite.setName(suiteName);
        xmlSuite.setParallel(parallelMode);
        xmlSuite.setVerbose(suiteVerbose);
        xmlSuite.setThreadCount(suiteThreadCount);

        /** Configure Test */
        XmlTest xmlTest = new XmlTest(xmlSuite);
        xmlTest.setName(testName);
        xmlTest.setPreserveOrder(true);

        /** Configure Test Params */
        Map<String, String> params = new HashMap<>();
        params.put("deviceId", deviceId);
        params.put("deviceName", deviceName);
        xmlTest.setParameters(params);

        /** Configure Classes */
        XmlClass xmlClass = new XmlClass(testClass);

        List<XmlClass> classes = new ArrayList<>();
        classes.add(xmlClass);

        xmlTest.setClasses(classes);

        TestNG testNG = new TestNG();

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(xmlSuite);

        testNG.setXmlSuites(suites);
        testNG.run();
    }
}
