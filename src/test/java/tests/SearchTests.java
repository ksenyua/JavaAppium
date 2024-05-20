package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests searching articles")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Testing search of articles")
    @Description("We initiate search by typing 'Java' and wait for search results")
    @Step("Starting test testSearch")
    public void testSearch()
    {
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.click_button_skip();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Testing cancel search of articles")
    @Description("We initiate search by typing 'Java' and click Cancel and wait for cancel button to disappear")
    @Step("Starting test testCancelSearch")
    public void testCheckCancelSearch(){
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.click_button_skip();
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmmountOfFoundArticle();
        Assert.assertTrue(
                "Find results less than 2",
                amount_of_search_results >=2
        );
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}

