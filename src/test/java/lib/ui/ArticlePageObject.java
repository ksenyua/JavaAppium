package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
    TITLE,
    FOOTER_ELEMENT,
    SAVE_TO_MY_LIST_BUTTON,
    ADD_TO_LIST_LINK,
    MY_LIST_NAME_INPUT,
    MY_LIST_OK_BUTTON,
    MY_LIST_FOLDER_BY_NAME_TPL,
    OPTIONS_BUTTON,
    OPTIONS_ADD_TO_MY_LIST_BUTTON,
    ADD_ARTICLE_TO_MY_LIST_BUTTON,
    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
    CLOSE_ARTICLE_BUTTON,
    ADD_TO_MY_LIST_OVERLAY,
    DESCRIPTION,
    CREATE_READING_LIST_BUTTON,
    NAVIGATE_UP_BUTTON,
    CANCEL_BUTTON,
    CREATED_FOLDER,
    MY_LIST_TITLE_TPL;


    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article on page", 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
        return title_element.getAttribute("name");
        } else {
            return title_element.getText();}
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find end of the article",
                    60
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find end of the article",
                    60
            );
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find the end of article", 20);
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open more option",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add to reading list",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it!' tip overlay",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input of name articles folder",
                5
        );
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void clickCancel() {
        this.waitForElementAndClick(
                CANCEL_BUTTON,
                "Cannot find cancel button",
                5);
    }

    public void addArticleToMySaved(){
        if (Platform.getInstance().isMw()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 15);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    ADD_ARTICLE_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before",
                    10
            );
        }}

    public void closeArticle()
    {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    NAVIGATE_UP_BUTTON,
                    "Cannot close article",
                    5);
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    private static String getFolderXpathByName(String name_of_folder)
    {
        return MY_LIST_FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    public void addArticleToMyListToFolderByName(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open more option",
                5
        );
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add to reading list",
                5
        );
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    public WebElement waitForDescriptionElement() {
        return this.waitForElementPresent(DESCRIPTION, "Cannot find article description", 15);
    }

    public String getArticleDescription() {
        WebElement description_element = waitForDescriptionElement();
        return description_element.getAttribute("name");
    }

    public void assertArticleTitlePresent()
    {
        this.assertElementPresent(
                TITLE,
                "We not found title"
        );
    }

    public void addArticlesToMySaved() {
        this.waitForElementAndClick(
                SAVE_TO_MY_LIST_BUTTON,
                "Cannot find button to save article",
                5
        );
    }
}


