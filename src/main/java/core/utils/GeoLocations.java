package core.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.html5.Location;


public class GeoLocations {

    /**
     * This class is used for mocking device Geo Location.
     * Coordinates below is based on Google Maps.
     * */

    public enum Locations {
        TEL_AVIV_UNIVERSITY, BEER_SHEVA_CENTRAL, GINI_APPS, PRINCESS_HOTEL_EILAT, TEL_AVIV_AZRIELI, MAMILLA_JERUSALEM, HAIFA, RISHON_LEZION, SOFIA_BULGARIA;
    }

    public static void setNewLocation(AppiumDriver driver, Locations locations) {

        Location someLocation;

        switch (locations) {
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

            case SOFIA_BULGARIA:
                someLocation = new Location(42.69770819999999, 23.321867500000053, 0);
                break;

            default:
                throw new RuntimeException("Location Unknown");
        }

        driver.setLocation(someLocation);
    }

    public static void setNewLocation(AppiumDriver driver, double latitude, double longitude, double altitude) {
        Location location = new Location(latitude, longitude, altitude);
        driver.setLocation(location);
    }
}
