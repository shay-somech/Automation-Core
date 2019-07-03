package core.utils;

import core.managers.drivers.DriverManager;
import io.appium.java_client.HasSettings;
import io.appium.java_client.Setting;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageLocatorBuilder {

    /**
     * Appium Docs: http://appium.io/docs/en/advanced-concepts/image-elements/
     * AppiumPro edition:
     *      Part 1: https://appiumpro.com/editions/32
     *      Part 2: https://appiumpro.com/editions/33
     */

    private HasSettings driver = (HasSettings) DriverManager.getDriver();

    /**
     * Convert image file into Base64 encoded file
     *
     * @param imageRelativePath image relative path (usually will be @resources)
     * @return Base64 encoded image
     */
    String getReferenceImageB64FromImageFile(String imageRelativePath) {
        try {

            URL refImgUrl = getClass().getClassLoader().getResource(imageRelativePath);
            assert refImgUrl != null;
            File refImgFile = Paths.get(refImgUrl.toURI()).toFile();
            assert refImgFile != null;
            return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    String getReferenceImageB64FromImageUrl(String imageUrl) {
        try {

            URL url = new URL(imageUrl);
            URLConnection ucon = url.openConnection();
            InputStream is = ucon.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read;
            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            baos.flush();
            return Base64.getEncoder().encodeToString(baos.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * It can happen that a matched image changes position in between the time it is found and the time you tap on it.
     * As with the previous setting, Appium can automatically adjust its position if it determines in a re-match that the position changed.
     *
     * @param value boolean switch on/off.
     * @return this in order to chain commands.
     */
    public ImageLocatorBuilder withUpdateImageElementPosition(boolean value) {
        driver.setSetting(Setting.UPDATE_IMAGE_ELEMENT_POSITION, value);
        return this;
    }

    /**
     * It can happen that, in between the time you have matched an image element and the time you choose to tap on it, the element is no longer present.
     * The only way for Appium to determine this is to attempt to re-match the template immediately before tapping.
     * If that re-match fails, you will get a StaleElementException, as you would expect.
     * Turn this to false to skip the check, potentially speeding things up, but potentially running into stale element issues without the benefit of an exception to let you know you did.
     *
     * @param value boolean switch on/off.
     * @return this in order to chain commands.
     */
    public ImageLocatorBuilder withCheckImageElementStaleness(boolean value) {
        driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS, value);
        return this;
    }

    /**
     * OpenCV will not allow the matching of a reference image / template if that image is larger than the base image to match against.
     * It can happen that the reference image you send in has dimensions which are larger than the screenshot Appium retrieves.
     * In this case the match will automatically fail.
     * If you set this setting to true, Appium will resize the template to ensure it is at least smaller than the size of the screenshot.
     *
     * @param value boolean switch on/off.
     * @return this in order to chain commands.
     */
    public ImageLocatorBuilder withFixImageTemplateSize(boolean value) {
        driver.setSetting(Setting.FIX_IMAGE_TEMPLATE_SIZE, value);
        return this;
    }

    /**
     * Appium knows the screen dimensions, and ultimately these are the dimensions which are relevant for deciding where to tap on the screen.
     * If the screenshot retrieved (via Appium's native methods, or an external source) does not match the screen dimensions, this setting dictates that Appium will adjust the size of the screenshot to match, ensuring that matched elements are found at the correct coordinates.
     * Turn this setting off if you know it's not necessary, and Appium will forego the check, potentially speeding things up a bit.
     *
     * @param value boolean switch on/off.
     * @return this in order to chain commands.
     */
    public ImageLocatorBuilder withFixScreenshotAndDeviceMismatches(boolean value) {
        driver.setSetting(Setting.FIX_IMAGE_FIND_SCREENSHOT_DIMENSIONS, value);
        return this;
    }

    /**
     * The OpenCV match threshold below which to consider the find a failure.
     * Basically the range of possibilities is between 0 (which means no threshold should be used) and 1 (which means that the reference image must be an exact pixel-for-pixel match).
     * The exact values in between have no absolute meaning.
     * For example a match that requires drastic resizing of a reference image will come out as a lower match strength than otherwise.
     * It's recommended you try the default setting, and then incrementally lower the threshold if you're not finding matching elements.
     * If you're matching the wrong element, try increasing the threshold value.
     *
     * @param value range between 0 - 1.
     * @return this in order to chain commands.
     */
    public ImageLocatorBuilder withSetImageMatchThreshold(int value) {
        driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, value);
        return this;
    }

    /**
     * In order to tap on a found image element, Appium has to use one of its touch action strategies.
     * The available strategies are the W3C Actions API, or the older MJSONWP TouchActions API.
     * Stick to the default unless the driver you are using does not support the W3C Actions API for some reason.
     *
     * @return this in order to chain commands.
     */
    public ImageLocatorBuilder withTapElementStrategy() {
        driver.setSetting(Setting.IMAGE_ELEMENT_TAP_STRATEGY, "touchActions");
        return this;
    }
}
