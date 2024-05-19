package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

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
    CLOSE_ARTICLE_BUTTON,
    ADD_TO_MY_LIST_OVERLAY,
    DESCRIPTION,
    MY_LIST_TITLE_TPL;


    public ArticlePageObject(AppiumDriver driver)
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
        } else {
        return title_element.getAttribute("name");}
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find end of the article",
                    60
            );
        } else {
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find end of the article",
                    60
            );
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

    public void addArticleToMySaved(){
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot find button X to close",
                5
        );
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


