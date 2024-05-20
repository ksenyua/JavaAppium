package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests of changing app conditions")
public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Article"),@Feature(value = "Search"),@Feature(value = "Rotation")})
    @DisplayName("Testing Title of Article after changing screen orientation")
    @Description("We open an article and change screen orientation and make sure that Title of Article have not been changed after rotation")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.MINOR)
    public void testChangeScreenOrientationOnSearchResults() {
        if (Platform.getInstance().isMw()) {
            return;
        }
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.click_button_skip();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String description_before_rotation = articlePageObject.getArticleDescription();

        this.rotateScreenLandscape();

        String description_after_rotation = articlePageObject.getArticleDescription();
        Assert.assertEquals(
                "Article description have been change after rotation",
                description_before_rotation,
                description_after_rotation
        );

        this.rotateScreenPortrait();

        String description_after_second_rotation = articlePageObject.getArticleDescription();
        Assert.assertEquals(
                "Article description have been change after rotation",
                description_before_rotation,
                description_after_second_rotation
        );
    }

    @Test
    @Features(value = {@Feature(value = "Article"),@Feature(value = "Search"),@Feature(value = "Background")})
    @DisplayName("Testing search results after going to Background")
    @Description("We initiate search of articles and wait for search results and send mobile app to background")
    @Step("Starting test testCheckSearchArticleInBackground")
    public void testCheckSearchArticleInBackground() {
        if (Platform.getInstance().isMw()) {
            return;
        }
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.click_button_skip();
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
