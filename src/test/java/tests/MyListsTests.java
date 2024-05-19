package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    String name_of_folder = "Learning programming";

    @Test
    public void testSaveTwoArticleAndDeleteOne()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String title_article_one = "British journalist";
        String title_article_two = "British archaeologist";
        String name_of_folder = "British famous people";
        String search_line_find_one = "Dilys Powell";
        String search_line_find_two = "Humfry Payone";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_find_one);
        if(Platform.getInstance().isAndroid()) {
            SearchPageObject.clickByArticleWithSubstring(title_article_one);
        } else {
            SearchPageObject.clickByArticleWithSubstring(search_line_find_one);
        }

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();
        if(Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_find_two);
        SearchPageObject.clickByArticleWithSubstring(title_article_two);
        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        ArticlePageObject.closeArticle();

        if(Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        if(Platform.getInstance().isIOS()){
            NavigationUI.clickCloseButtonOnPopup();
        }
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(title_article_two);
        MyListsPageObject.waitForArticleAppearByTitle(title_article_one);
        if(Platform.getInstance().isAndroid()){
        String title_expected = MyListsPageObject.getArticleTitleMyList();
        MyListsPageObject.clickArticleByTitle(title_article_one);

        String title_result = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title " + title_result + "cannot expected",
                title_expected,
                title_result
        );}
        else {
            MyListsPageObject.checkRightArticleWasDeleted();
        }
    }
}

