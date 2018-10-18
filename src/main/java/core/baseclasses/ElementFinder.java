package core.baseclasses;

import core.constants.FindByLocator;
import core.utils.ElementWrapper;
import core.utils.XpathGenerator;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

import java.util.List;

public class ElementFinder {

    public static ElementWrapper findElementBy(FindByLocator findBy, String element) {
        switch (findBy) {
            case NAME:
                return new ElementWrapper(By.name(element));

            case TEXT:
                return new ElementWrapper(XpathGenerator.getXpathByText(element));

            case ID:
                return new ElementWrapper(By.id(element));

            case XPATH:
                return new ElementWrapper(By.xpath(element));

            case CLASS:
                return new ElementWrapper(By.className(element));

            case ACCESSIBILITY_IDENTIFIER:
                return new ElementWrapper(new MobileBy.ByAccessibilityId(element));

            case LABEL:
                return new ElementWrapper(XpathGenerator.getXpathByAccessibilityLabel(element));

            case ACCESSIBILITY_LABEL:
                return new ElementWrapper(XpathGenerator.getXpathByAccessibilityLabel(element));

            case PARTIAL_TEXT:
                return new ElementWrapper(XpathGenerator.getXpathByPartialText(element));

            case CONTENT_DESCRIPTION:
                return new ElementWrapper(XpathGenerator.getXpathByDescription(element));
        }

        throw new RuntimeException("Element Locator is not defined");
    }

    public static ElementWrapper findElementBy(FindByLocator findBy, String element, String element2) {
        switch (findBy) {

            case RESOURCE_ID_AND_TEXT:
                return new ElementWrapper(XpathGenerator.getXpathByIdAndText(element, element2));

            case RESOURCE_ID_AND_PARTIAL_TEXT:
                return new ElementWrapper(XpathGenerator.getXpathByIdAndPartialText(element, element2));

            case ACCESSIBILITY_LABEL_AND_TEXT:
                return new ElementWrapper(XpathGenerator.getXpathByAccessibilityLabelAndText(element, element2));

            case ACCESSIBILITY_IDENTIFIER_AND_TEXT:
                return new ElementWrapper(XpathGenerator.getXpathByAccessibilityIdentifierAndText(element, element2));
        }

        throw new RuntimeException("Element Locator is not defined");
    }

    public static List findElementsBy(FindByLocator findBy, String element) {
        return ElementWrapper.findElements(findBy, element);
    }
}
