package Tests;

import core.managers.MyLogger;
import org.testng.annotations.Test;

public class Testing {

    @Test
    public void test() {
        MyLogger.logSys("Success (from Test Package)!!");
    }
}
