package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    String name_of_folder = "Learning programming";

    public void testSaveFirstArticleToMyList() {

        String login = "ksu_0304";
        String password = "AppiumTest";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String article_title = ArticlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
            if (Platform.getInstance().isMw()) {
                AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
                Auth.clickAuthButton();
                Auth.enterLoginData(login, password);
                Auth.submitForm();
            }

            ArticlePageObject.closeArticle();
            if (Platform.getInstance().isAndroid()) {
                SearchPageObject.initSearchInput();
                SearchPageObject.typeSearchLine("Java");
                SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
                if (Platform.getInstance().isAndroid()) {
                    ArticlePageObject.addArticleToMyList(name_of_folder);
                } else {
                    ArticlePageObject.addArticlesToMySaved();

                    ArticlePageObject.waitForTitleElement();
                    Assert.assertEquals("We are not on the same page after login",
                            article_title,
                            ArticlePageObject.getArticleTitle());
                    ArticlePageObject.addArticlesToMySaved();
                }

                ArticlePageObject.closeArticle();
            } else {
                ArticlePageObject.clickCancel();
            }
            NavigationUI.clickMyLists();
            if(Platform.getInstance().isAndroid()) {
                MyListsPageObject.openFolderByName(name_of_folder);
            }
            MyListsPageObject.swipeByArticleToDelete(article_title);
            if (Platform.getInstance().isAndroid()) {
                ArticlePageObject.closeArticle();
            } else {
                ArticlePageObject.clickCancel();
            }
            NavigationUI.openNavigation();
            NavigationUI.clickMyLists();
            if (Platform.getInstance().isAndroid()) {
                MyListsPageObject.openFolderByName(name_of_folder);
            } else {
                MyListsPageObject.swipeByArticleToDelete(article_title);
            }
        }}

    @Test
    public void testSaveTwoArticleAndDeleteOne()
    {
        String login = "ksu_0304";
        String password = "AppiumTest";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String title_article_one = "British journalist";
        String title_article_two = "British archaeologist";
        String name_of_folder = "British famous people";
        String search_line_find_one = "ilys Powell";
        String search_line_find_two = "umfry Payone";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_find_one);
        if(Platform.getInstance().isAndroid()) {
            SearchPageObject.clickByArticleWithSubstring(title_article_one);
        } else if (Platform.getInstance().isIOS()){
            SearchPageObject.clickByArticleWithSubstring(search_line_find_one);
        } else {
            SearchPageObject.clickByArticleWithSubstringByTitle(search_line_find_one);
        }

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }
        if(Platform.getInstance().isMw()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
        }
        if(Platform.getInstance().isMw()) {
            ArticlePageObject.addArticleToMySaved();
        }

        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeArticle();
        }
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
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        if(Platform.getInstance().isIOS()){
            NavigationUI.clickCloseButtonOnPopup();
        }
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            MyListsPageObject.swipeByArticleToDelete(title_article_two);
        } else{
            MyListsPageObject.swipeByArticleToDelete(search_line_find_two);
        }
        if(Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            MyListsPageObject.waitForArticleAppearByTitle(title_article_one);
        } else {
            MyListsPageObject.waitForArticleAppearByTitle(search_line_find_one);
        }
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isMw()) {
        String title_expected = MyListsPageObject.getArticleTitleMyList();
        MyListsPageObject.clickArticleByTitle(title_article_one);

        String title_result = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Article title " + title_result + "cannot expected",
                title_expected,
                title_result
        );}
        else {
            MyListsPageObject.checkRightArticleWasDeleted();
        }
    }
}

