package Tests;

import core.baseclasses.Runner;
import core.managers.drivers.DriverManager;
import core.utils.GeoLocations;
import core.utils.Log;
import org.testng.annotations.Test;

import static core.utils.GeoLocations.Locations.SOFIA_BULGARIA;

public class Testing extends Runner {

    @Test
    public void test() {
        Log.info("Setting location to Sofia, Bulgaria");
        GeoLocations.setNewLocation(DriverManager.getDriver(), SOFIA_BULGARIA);
        Log.info("Success :)");
    }
}
