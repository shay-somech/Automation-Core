import core.managers.MyLogger;
import core.managers.baseclasses.Runner;
import org.testng.annotations.Test;

public class Testing extends Runner {

    @Test
    void test() {
        MyLogger.logSys("Success !!");
    }
}
