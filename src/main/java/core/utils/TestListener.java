package core.utils;

import core.baseclasses.Launcher;
import core.managers.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.testng.*;

import java.util.Map;

public class TestListener implements ISuiteListener, ITestListener, IInvokedMethodListener, ITestNGListener {

    /**
     * Suite Listeners
     */

    @Override
    public void onStart(ISuite suite) {
        Launcher.start();
    }

    @Override
    public void onFinish(ISuite suite) {
        Launcher.tearDown();
    }


    /**
     * Test Listeners
     */

    @Override
    public void onTestStart(ITestResult result) {
        Log.warn("Starting Test: " + getTestMethodName(result));
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.warn(getTestMethodName(result) + " Passed Successfully! :) ");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error(getTestMethodName(result) + " Failed! :( ");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.warn(getTestMethodName(result) + " Skipped! ");
    }


    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }


    /**
     * Method Listeners
     */

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        showMessage("About to run ", method, testResult);
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        showMessage("Completed running ", method, testResult);
    }


    private static void showMessage(String prefix, IInvokedMethod method, ITestResult testResult) {
        String msg;
        if (getTestXmlParams(method, testResult) != null) {
            msg = prefix + method.getTestMethod().getMethodName() + "() with the parameters " + getTestXmlParams(method, testResult);
        } else {
            msg = prefix + method.getTestMethod().getMethodName();
        }
        Log.warn(msg);
    }

    private static Map<String, String> getTestXmlParams(IInvokedMethod method, ITestResult testResult) {
        if (method.getTestMethod().findMethodParameters(testResult.getTestContext().getCurrentXmlTest()).isEmpty()) {
            return null;
        } else {
            return method.getTestMethod().findMethodParameters(testResult.getTestContext().getCurrentXmlTest());
        }
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    private byte[] saveScreenShotAsPNG() {
        return (DriverManager.getDriver().getScreenshotAs(OutputType.BYTES));
    }

    private static String saveTextLog(String message) {
        return message;
    }
}
