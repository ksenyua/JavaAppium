package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
            MY_LIST_LINK,
            CLOSE_BUTTON_POPUP,
            NOT_NOW_BUTTON,
            OPEN_NAVIGATION,
            //HOME_LINK;
            SKIP_BUTTON = "xpath://*[contains(@text,'Skip')]";


    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }

    @Step("Clicking on my lists")
    public void clickMyLists()
    {
        if (Platform.getInstance().isMw()) {
            this.tryClickElementWithFewAttempts(
                    MY_LIST_LINK,
                    "Cannot find navigation button to my list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LIST_LINK,
                    "Cannot find navigation button to my list",
                    5);
            this.waitForElementAndClick(
                    NOT_NOW_BUTTON,
                    "Cannot find Not now button",
                    5);
        }
    }

    @Step("Clicking on button close popup")
    public void clickCloseButtonOnPopup()
    {
        this.waitForElementAndClick(
                CLOSE_BUTTON_POPUP,
                "Cannot find close button on alert",
                5
        );
    }

    @Step("Opening navigation on mobile web page")
    public void openNavigation() {
        if (Platform.getInstance().isMw()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigationbutton", 5);
        } else {
            System.out.println("Method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void click_button_skip()
    {
        this.waitForElementAndClick(
                SKIP_BUTTON,
                "Cannot find skip button",
                5
        );
    }

}
