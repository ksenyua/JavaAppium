package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
            MY_LIST_LINK,
            CLOSE_BUTTON_POPUP,
            //HOME_LINK;
            SKIP_BUTTON = "xpath://*[contains(@text,'Skip')]";


    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyLists()
    {
        this.waitForElementAndClick(
                MY_LIST_LINK,
                "Cannot find navigation button to My List",
                5
        );
    }

    public void clickCloseButtonOnPopup()
    {
        this.waitForElementAndClick(
                CLOSE_BUTTON_POPUP,
                "Cannot find close button on alert",
                5
        );
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
