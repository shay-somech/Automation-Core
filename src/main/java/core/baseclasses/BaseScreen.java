package core.baseclasses;

import core.managers.drivers.AndroidDriverManager;
import core.utils.ElementWrapper;
import core.utils.XpathGenerator;

public class BaseScreen {

    /**
     * provide element created from xPath generated from provided id or label depends on platform
     *
     * @param id id of element want to be found
     * @return element found by provided id
     */
    public ElementWrapper getElementByPlatformId(String id) {
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
    public ElementWrapper getElementByLabel(String label) {
        return new ElementWrapper(XpathGenerator.getXpathByLabel(label));
    }

    public ElementWrapper getElementByAccessibilityLabel(String label) {
        return new ElementWrapper(XpathGenerator.getXpathByAccessibilityLabel(label));
    }

    public ElementWrapper getElementByAccessibilityIdentifier(String label) {
        return new ElementWrapper(XpathGenerator.getXpathByAccessibilityIdentifier(label));
    }

    public ElementWrapper getElementByLabel(String label, boolean onScreen) {
        return new ElementWrapper(XpathGenerator.getXpathByLabel(label), onScreen);
    }


    /**
     * provide element created from xPath generated from provided id
     *
     * @param id id of element want to be found
     * @return element found by provided id
     * @boolean onScreen determines if we want to search for element on screen
     */
    public ElementWrapper getElementById(String id) {
        return new ElementWrapper(XpathGenerator.getXpathById(id));
    }

    public ElementWrapper getElementById(String id, boolean onScreen) {
        return new ElementWrapper(XpathGenerator.getXpathById(id), onScreen);
    }


    /**
     * provide element created from xPath generated from provided contentDescription
     *
     * @param description id of element want to be found
     * @return element found by provided description
     * @boolean onScreen determines if we want to search for element on screen
     */
    public ElementWrapper getElementByContentDescription(String description) {
        return new ElementWrapper(XpathGenerator.getXpathByDescription(description));
    }

    public ElementWrapper getElementByContentDescription(String description, boolean onScreen) {
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
    public ElementWrapper getElementByIdAndText(String id, String text) {
        return new ElementWrapper(XpathGenerator.getXpathByIdAndText(id, text));
    }

    public ElementWrapper getElementByIdAndText(String id, String text, boolean onScreen) {
        return new ElementWrapper(XpathGenerator.getXpathByIdAndText(id, text), onScreen);
    }

    /**
     * provide element created from xPath generated from provided text
     *
     * @param text text of element want to be found
     * @return element found by provided text
     * @boolean onScreen determines if we want to search for element on screen
     */
    public ElementWrapper getElementByText(String text) {
        return new ElementWrapper(XpathGenerator.getXpathByText(text));
    }

    public ElementWrapper getElementByText(String text, boolean onScreen) {
        return new ElementWrapper(XpathGenerator.getXpathByText(text), onScreen);
    }


    /**
     * provide element created from xPath generated from provided text
     *
     * @param text part ot full text of element want to be found
     * @return element found by provided text
     * @boolean onScreen determines if we want to search for element on screen
     */
    public ElementWrapper getElementByPartialText(String text) {
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
    public ElementWrapper getElementByIdAndPartialText(String id, String text) {
        return new ElementWrapper(XpathGenerator.getXpathByIdAndPartialText(id, text));
    }

    public ElementWrapper getElementByIdAndPartialText(String id, String text, boolean onScreen) {
        return new ElementWrapper(XpathGenerator.getXpathByIdAndPartialText(id, text), onScreen);
    }

    /**
     * @param xPath xPath of element want to be found
     * @return element found by provided xPath
     * @boolean onScreen determines if we want to search for element on screen
     */
    public ElementWrapper getElementByXpath(String xPath) {
        return new ElementWrapper(xPath);
    }

    public ElementWrapper getElementByXpath(String xPath, boolean onScreen) {
        return new ElementWrapper(xPath, onScreen);
    }


    /**
     * create element,find and make sure he displayed
     *
     * @param id id of element we want to be verified
     */
    public void verifyExistById(String id) {
        if (!getElementById(id).isExistAndDisplayed())
            throw new AssertionError("Can't verify (" + id + "), element absent or blocked");
    }


    /**
     * find and make element provided is displayed
     *
     * @param elementWrapper ElementWrapper object we want to be verified
     */
    public void verifyExistByElement(ElementWrapper elementWrapper) {
        if (elementWrapper.isExistAndDisplayed())
            throw new AssertionError("Can't verify (" + elementWrapper.getXpath() + "), element absent or blocked");
    }
}

