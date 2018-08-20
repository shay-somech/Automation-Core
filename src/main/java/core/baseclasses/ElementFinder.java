package core.baseclasses;

import core.managers.drivers.AndroidDriverManager;
import core.utils.ElementWrapper;
import core.utils.XpathGenerator;

public class ElementFinder {

    /**
     * provide element created from xPath generated from provided id or label depends on platform
     *
     * @param id id of element want to be found
     * @return element found by provided id
     */
    public static ElementWrapper getElementByPlatformId(String id) {
        if (AndroidDriverManager.isAndroid) {
            return getElementById(id);
        } else {
            return getElementByAccessibilityIdentifier(id);
        }
    }

    /**
     * provide element created from xPath generated from provided id
     *
     * @param label id of element want to be found
     * @return element found by provided id
     * @boolean onScreen determines if we want to search for element on screen
     */
    public static ElementWrapper getElementByLabel(String label) {
        return new ElementWrapper(XpathGenerator.getXpathByLabel(label));
    }

    public static ElementWrapper getElementByAccessibilityLabel(String label) {
        return new ElementWrapper(XpathGenerator.getXpathByAccessibilityLabel(label));
    }

    public static ElementWrapper getElementByAccessibilityLabelAndText(String label, String text) {
        return new ElementWrapper(XpathGenerator.getXpathByAccessibilityLabelAndText(label, text));
    }

    public static ElementWrapper getElementByAccessibilityIdentifier(String label) {
        return new ElementWrapper(XpathGenerator.getXpathByAccessibilityIdentifier(label));
    }

    public static ElementWrapper getElementByAccessibilityIdentifierAndText(String label, String text) {
        return new ElementWrapper(XpathGenerator.getXpathByAccessibilityIdentifierAndText(label, text));
    }

    public static ElementWrapper getElementByLabel(String label, boolean onScreen) {
        return new ElementWrapper(XpathGenerator.getXpathByLabel(label), onScreen);
    }


    /**
     * provide element created from xPath generated from provided id
     *
     * @param id id of element want to be found
     * @return element found by provided id
     * @boolean onScreen determines if we want to search for element on screen
     */
    public static ElementWrapper getElementById(String id) {
        return new ElementWrapper(XpathGenerator.getXpathById(id));
    }

    public static ElementWrapper getElementById(String id, boolean onScreen) {
        return new ElementWrapper(XpathGenerator.getXpathById(id), onScreen);
    }


    /**
     * provide element created from xPath generated from provided contentDescription
     *
     * @param description id of element want to be found
     * @return element found by provided description
     * @boolean onScreen determines if we want to search for element on screen
     */
    public static ElementWrapper getElementByContentDescription(String description) {
        return new ElementWrapper(XpathGenerator.getXpathByDescription(description));
    }

    public static ElementWrapper getElementByContentDescription(String description, boolean onScreen) {
        return new ElementWrapper(XpathGenerator.getXpathByDescription(description), onScreen);
    }


    /**
     * provide element created from xPath generated from provided id and text , only element with both parameters will be found
     *
     * @param id   id of element want to be found
     * @param text text of element want to be found
     * @return element found by provided id and text
     * @boolean onScreen determines if we want to search for element on screen
     */
    public static ElementWrapper getElementByIdAndText(String id, String text) {
        return new ElementWrapper(XpathGenerator.getXpathByIdAndText(id, text));
    }

    public static ElementWrapper getElementByPlatformIdAndText(String id, String text) {
        if (AndroidDriverManager.isAndroid) {
            return new ElementWrapper(XpathGenerator.getXpathByIdAndText(id, text));
        } else {
            return new ElementWrapper(XpathGenerator.getXpathByAccessibilityIdentifierAndText(id, text));
        }
    }

    public static ElementWrapper getElementByIdAndText(String id, String text, boolean onScreen) {
        return new ElementWrapper(XpathGenerator.getXpathByIdAndText(id, text), onScreen);
    }

    /**
     * provide element created from xPath generated from provided text
     *
     * @param text text of element want to be found
     * @return element found by provided text
     * @boolean onScreen determines if we want to search for element on screen
     */
    public static ElementWrapper getElementByText(String text) {
        return new ElementWrapper(XpathGenerator.getXpathByText(text));
    }

    public static ElementWrapper getElementByText(String text, boolean onScreen) {
        return new ElementWrapper(XpathGenerator.getXpathByText(text), onScreen);
    }


    /**
     * provide element created from xPath generated from provided text
     *
     * @param text part ot full text of element want to be found
     * @return element found by provided text
     * @boolean onScreen determines if we want to search for element on screen
     */
    public static ElementWrapper getElementByPartialText(String text) {
        return new ElementWrapper(XpathGenerator.getXpathByPartialText(text));
    }

    /**
     * provide element created from xPath generated from provided text
     *
     * @param text part ot full text of element want to be found
     * @param id   id of element want to be found
     * @return element found by provided text
     * @boolean onScreen determines if we want to search for element on screen
     */
    public static ElementWrapper getElementByIdAndPartialText(String id, String text) {
        return new ElementWrapper(XpathGenerator.getXpathByIdAndPartialText(id, text));
    }

    public static ElementWrapper getElementByAccessibilityIdentifierAndPartialText(String id, String text) {
        return new ElementWrapper(XpathGenerator.getXpathByAccessibilityIdAndPartialText(id, text));
    }

    public static ElementWrapper getElementByIdAndPartialText(String id, String text, boolean onScreen) {
        return new ElementWrapper(XpathGenerator.getXpathByIdAndPartialText(id, text), onScreen);
    }

    /**
     * @param xPath xPath of element want to be found
     * @return element found by provided xPath
     * @boolean onScreen determines if we want to search for element on screen
     */
    public static ElementWrapper getElementByXpath(String xPath) {
        return new ElementWrapper(xPath);
    }

    public static ElementWrapper getElementByXpath(String xPath, boolean onScreen) {
        return new ElementWrapper(xPath, onScreen);
    }

    /**
     * @param className of element want to be found
     * @return element found by provided className
     * @boolean onScreen determines if we want to search for element on screen
     */
    public static ElementWrapper getElementByClass(String className) {
        return new ElementWrapper(XpathGenerator.getXpathByClassName(className));
    }

    public static ElementWrapper getElementByClass(String xPath, boolean onScreen) {
        return new ElementWrapper(xPath, onScreen);
    }


    /**
     * create element,find and make sure he displayed
     *
     * @param id id of element we want to be verified
     */
    public static void verifyExistById(String id) {
        if (!getElementById(id).isExistAndDisplayed())
            throw new AssertionError("Can't verify (" + id + "), element absent or blocked");
    }


    /**
     * find and make element provided is displayed
     *
     * @param elementWrapper ElementWrapper object we want to be verified
     */
    public static void verifyExistByElement(ElementWrapper elementWrapper) {
        if (elementWrapper.isExistAndDisplayed())
            throw new AssertionError("Can't verify (" + elementWrapper.getXpath() + "), element absent or blocked");
    }
}

// TODO: Element by id should be generic for both platforms
// Elements by id & text should inherit from (element by id) + text