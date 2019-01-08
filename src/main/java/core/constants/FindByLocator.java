package core.constants;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public enum FindByLocator {
    TEXT,
    NAME,
    ID,
    XPATH,
    CLASS,
    ACCESSIBILITY_LABEL,
    ACCESSIBILITY_IDENTIFIER,
    PARTIAL_TEXT,
    CONTENT_DESCRIPTION,
    TEXT_AND_RESOURCE_ID,
    PARTIAL_TEXT_AND_RESOURCE_ID,
    ACCESSIBILITY_LABEL_AND_TEXT,
    ACCESSIBILITY_IDENTIFIER_AND_TEXT;

    public By getLocator(String element) {
        switch (this) {
            case ID:
                return By.id(element);

            case TEXT:
                return By.xpath("//*[@text='" + element + "']");

            case PARTIAL_TEXT:
                return By.xpath("//*[contains(text(),'" + element + "')]");

            case CLASS:
                return By.className(element);

            case XPATH:
                return By.xpath(element);

            case NAME:
                return By.name(element);

            case CONTENT_DESCRIPTION:
                return By.xpath("//*[@contentDescription='" + element + "']");

            case ACCESSIBILITY_IDENTIFIER:
                return new MobileBy.ByAccessibilityId(element);

            case ACCESSIBILITY_LABEL:
                return By.xpath("//*[@label='" + element + "']");

        }
        throw new RuntimeException("Locator is not defined in scope");
    }

    public By getMultipleLocators(String element, String element2) {
        switch (this) {
            case TEXT_AND_RESOURCE_ID:
                return By.xpath("//*[@text='" + element + "' and @resource-id='" + element2 + "']");

            case PARTIAL_TEXT_AND_RESOURCE_ID:
                return By.xpath("//*[contains(text(),'" + element + "') and @resource-id='" + element2 + "']");

            case ACCESSIBILITY_LABEL_AND_TEXT:
                return By.xpath("//*[@label='" + element + "' and @text='" + element2 + "']");

            case ACCESSIBILITY_IDENTIFIER_AND_TEXT:
                return By.xpath("//*[@accessibilityIdentifier='" + element + "' and @text='" + element2 + "']");

        }
        throw new RuntimeException("Locator is not defined in scope");
    }
}
