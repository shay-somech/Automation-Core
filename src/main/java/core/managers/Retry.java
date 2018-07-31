package core.managers;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int retryCount = 0;
    private final int maxRetryCount = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {                                   //Check if test not succeed
            if (retryCount < maxRetryCount) {                            //Check if maxRetryCount count is reached
                retryCount++;                                           //Increase the retryCount count by 1
                iTestResult.setStatus(ITestResult.FAILURE);            //Mark test as ignored
                return true;                                          //Tells TestNG to re-run the test
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);         //If maxRetryCount reached,test marked as failed
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);          //If test passes, TestNG marks it as passed
        }
        return false;
    }
}

// TODO: NEEDS FIXING REGARDING RESETTING THE APP UPON FAILURE
