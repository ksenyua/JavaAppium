package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
    SEARCH_INPUT = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']";
    SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[contains(text(),'{SUBSTRING}')]";
    SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
    //SEARCH_TITLE_AND_DESCRIPTION_RESULT_BY_SUBSTRING_TPL = "xpath://android.widget.TextView[contains(text(),'{SUBSTRING}')]";
    SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
    SEARCH_SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
    SEARCH_INPUT_TEXT = "id:org.wikipedia:id/search_src_text";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

}
