package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSNavigationUI extends NavigationUI {
    static {
        MY_LIST_LINK = "id:Saved";
        CLOSE_BUTTON_POPUP = "id:Close";
    }

    public iOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
