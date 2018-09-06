package Tests;

import core.baseclasses.Runner;
import core.managers.MyLogger;
import org.testng.annotations.Test;

public class Testing extends Runner {

    @Test
    public void test() {
        MyLogger.logSys("Success :)");
    }
}
