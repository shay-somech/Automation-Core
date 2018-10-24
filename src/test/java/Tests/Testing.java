package Tests;

import core.baseclasses.Runner;
import core.utils.Log;
import org.testng.annotations.Test;

public class Testing extends Runner {

    @Test
    public void test() {
        Log.info("Success :)");
    }
}
