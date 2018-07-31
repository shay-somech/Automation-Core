package core.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.html5.Location;


public class GeoLocations {

    /**
     * This class is used for mocking device Geo Location.
     * Coordinates below is based on Google Maps.
     * */


    private static final String TEL_AVIV_UNIVERSITY = "Tel-Aviv University";
    private static final String BEER_SHEVA_CENTRAL = "Beer-Sheva Central";
    private static final String GINI_APPS = "Gini-Apps";
    private static final String PRINCESS_HOTEL_EILAT = "Princess Hotel Eilat";
    private static final String TEL_AVIV_AZRIELI = "Tel-Aviv Azrieli";
    private static final String MAMILLA_JERUSALEM = "Mamilla BLVD Jerusalem";
    private static final String HAIFA = "Haifa_Central";
    private static final String RISHON_LEZION = "Rishon Lezion Central";

    public static void newLocation(AppiumDriver driver, String location) {
        Location someLocation;
        switch (location) {
            case TEL_AVIV_UNIVERSITY:
                someLocation = new Location(32.111767, 34.801361, 0);
                break;

            case TEL_AVIV_AZRIELI:
                someLocation = new Location(32.074573, 34.79181, 0);
                break;

            case BEER_SHEVA_CENTRAL:
                someLocation = new Location(31.252973, 34.791462, 0);
                break;

            case GINI_APPS:
                someLocation = new Location(32.163808, 34.808611, 0);
                break;

            case PRINCESS_HOTEL_EILAT:
                someLocation = new Location(29.557742, 34.951595, 0);
                break;

            case MAMILLA_JERUSALEM:
                someLocation = new Location(31.776117, 35.223456, 0);
                break;

            case HAIFA:
                someLocation =  new Location(32.788542, 34.990351, 0);
                break;

            case RISHON_LEZION:
                someLocation = new Location(31.973001, 34.792501, 0);
                break;

            default:
                throw new RuntimeException("Location Unknown");
        }

        driver.setLocation(someLocation);
    }
}
