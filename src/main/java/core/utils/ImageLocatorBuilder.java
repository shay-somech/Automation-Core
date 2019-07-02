package core.utils;

import core.managers.drivers.DriverManager;
import io.appium.java_client.HasSettings;
import io.appium.java_client.Setting;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageLocatorBuilder {

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
            BufferedInputStream bis = new BufferedInputStream(url.openConnection().getInputStream());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read;
            while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
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
     * Refresh elements automatically *
     * By default, Appium will let you know with an exception if an image element is stale.
     * But what if the element has not disappeared, only changed position? Maybe it's OK to simply tap on it at its new location without further ado.
     * If you'd prefer to have Appium automatically determine a new match location for image elements when you request a tap, you can use this method
     */
    public ImageLocatorBuilder withUpdateImageElementPosition(boolean value) {
        driver.setSetting(Setting.UPDATE_IMAGE_ELEMENT_POSITION, value);
        return this;
    }

    /**
     * Check for image element staleness *
     * Something you might be familiar with from the Selenium world is the concept of a "stale element".
     * This is an element which, in the time between its being found and its being interacted with, has somehow disappeared.
     * In this case it's not possible to complete the desired interaction (tap, send keys, etc...), and so a StaleElementException is thrown.
     * If you'd prefer to get a bit of a performance improvement and do away with this safety check, you can always turn it off
     */
    public ImageLocatorBuilder withCheckImageElementStaleness(boolean value) {
        driver.setSetting(Setting.CHECK_IMAGE_ELEMENT_STALENESS, value);
        return this;
    }

    /**
     * Fix the reference image size *
     * It's necessary for the reference image (template) to have a size smaller than the screenshot for the algorithm to work
     * If you want to be sure that you can use your template image, no matter what size it happens to be, let Appium know it's OK to resize it for you with this setting
     */
    public ImageLocatorBuilder withFixImageTemplateSize(boolean value) {
        driver.setSetting(Setting.FIX_IMAGE_TEMPLATE_SIZE, value);
        return this;
    }

    /**
     * Fix screenshot and device size mismatches *
     * Getting these dimensions matched correctly is so important that Appium does it by default.
     * If, however, you'd prefer not to have Appium spend the CPU cycles making this happen, you can opt out
     */
    public ImageLocatorBuilder withFixScreenshotAndDeviceMismatches(boolean value) {
        driver.setSetting(Setting.FIX_IMAGE_FIND_SCREENSHOT_DIMENSIONS, value);
        return this;
    }

    /**
     * Change the image match threshold *
     * The scale itself is arbitrary, but 1 represents a pixel-for-pixel perfect match, and 0 represents no comparability whatsoever.
     * With a little bit of experimentation, Appium's default match threshold has been set to 0.4.
     * This means that, by default, any match attempt which results in a similarity measure of less than 0.4 will be rejected.
     * This method lets you decide what the threshold should be
     */
    public ImageLocatorBuilder withSetImageMatchThreshold(int value) {
        driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, value);
        return this;
    }

    /**
     * Change the image element tap strategy *
     * If Appium's default tap strategy (using W3C Actions) does not work for you
     * (say because you're using a driver which has not been updated to support the W3C Actions API), you can always fall back to the older API, using this method
     */
    public ImageLocatorBuilder withTapElementStrategy() {
        driver.setSetting(Setting.IMAGE_ELEMENT_TAP_STRATEGY, "touchActions");
        return this;
    }
}
