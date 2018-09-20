package core.utils;


import core.managers.DisplayManager;

public class XpathGenerator {

    /**
     * @param id   id of element we want to generate xPath for
     * @param text text of element we want to generate xPath for
     * @return xPath String generated from both id and text provided
     */
    public static String getXpathByIdAndText(String id, String text) {
        return "//*[@text='" + text + "' and @resource-id='" + id + "']";
    }

    /**
     * @param id   id of element we want to generate xPath for
     * @param text text or part of the text of element we want to generate xPath for
     * @return xPath String generated from both id and text provided
     */
    public static String getXpathByIdAndPartialText(String id, String text) {
        return "//*[contains(text(),'" + text + "') and @resource-id='" + id + "']";
    }

    /**
     * For iOS
     */
    public static String getXpathByAccessibilityIdAndPartialText(String id, String text) {
        return "//*[contains(text(),'" + text + "') and @accessibilityIdentifier='" + id + "']";
    }

    /**
     * @param text text or part of the text of element we want to generate xPath for
     * @return xPath String generated from both id and text provided
     */
    public static String getXpathByPartialText(String text) {
        return "//*[contains(text(),'" + text + "')]";
    }


    /**
     * @param id id of element we want to generate xPath for
     * @return xPath String generated from id provided
     */
    public static String getXpathById(String id) {
        return "//*[@resource-id='" + id + "']";
    }

    /**
     * @param label id of element we want to generate xPath for
     * @return xPath String generated from label provided
     */
    public static String getXpathByLabel(String label) {
        return "//*[@label='" + label + "']";
    }

    /**
     * @param label id of element we want to generate xPath for
     * @return xPath String generated from label provided
     */
    public static String getXpathByAccessibilityLabel(String label) {
        return "//*[@label='" + label + "']";
    }

    /**
     * @param label id of element we want to generate xPath for
     * @return xPath String generated from label provided
     */
    public static String getXpathByAccessibilityLabelAndText(String label, String text) {
        return "//*[@label='" + label + "' and @text='" + text + "']";
    }

    /**
     * @param label id of element we want to generate xPath for
     * @return xPath String generated from label provided
     */
    public static String getXpathByClassName(String label) {
        return "//*[@class='" + label + "']";
    }


    /**
     * @param text text of element we want to generate xPath for
     * @return xPath String generated from text provided
     */
    public static String getXpathByText(String text) {
        return "//*[@text='" + text + "']";
    }

    /**
     * @param description contentDescription of element we want to generate xPath for
     * @return xPath String generated from contentDescription provided
     */
    public static String getXpathByDescription(String description) {
        return "//*[@contentDescription='" + description + "']";
    }

    /**
     * @param onScreen is element need to be shown or not
     * @return xPath of screen dimensions that shown on screen
     */
    static String getOnScreenParam(boolean onScreen) {
        if (onScreen) {
            return "[@onScreen = 'true']" + getVisibleScreenParam();
        } else
            return "[@onScreen = 'false']";
    }

    /**
     * @return xPath of screen dimensions
     */
    private static String getVisibleScreenParam() {
        return "[@x <= '" + DisplayManager.getInstance().getDeviceWidth() + "']" + "[@y <= '" + DisplayManager.getInstance().getDeviceHeight() + "']";
    }

    public static String getXpathByAccessibilityIdentifier(String label) {
        return "//*[@accessibilityIdentifier='" + label + "']";
    }

    public static String getXpathByAccessibilityIdentifierAndText(String id, String text) {
        return "//*[@text='" + text + "' and @accessibilityIdentifier='" + id + "']";
    }
}
