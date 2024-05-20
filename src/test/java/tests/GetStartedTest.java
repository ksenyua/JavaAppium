package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

@Epic("Tests of passing through welcome page")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "WelcomePage")})
    @DisplayName("Testing pass through welcome page")
    @Description("We pass through welcome page")
    @Step("Starting test testPassThroughWelcome")
    public void testPassThroughWelcome() {

        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMw())) {
            return;
        }
        WelcomePageObject welcomePage = new WelcomePageObject(driver);

        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();

        welcomePage.waitForNewWaysToExploreText();
        welcomePage.clickNextButton();

        welcomePage.waitForAddOrEditPreferredLanguagesText();
        welcomePage.clickNextButton();

        welcomePage.waitForLearnMoreAboutDataCollectedText();
        welcomePage.clickGetStartedButton();
    }
}
