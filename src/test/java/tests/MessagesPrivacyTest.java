package tests;

import core.pages.MessageMainPage;
import core.pages.SessionMainPage;
import helper.ResourceLoader;
import model.TestBot;
import org.junit.Test;
import org.testng.Assert;

public class MessagesPrivacyTest extends TestBase {

    TestBot testBot1 = ResourceLoader.getTestBot("testBot1"),
            testBot2 = ResourceLoader.getTestBot("testBot2");

    @Test
    public void messagesPrivacyTest() throws InterruptedException {

        new SessionMainPage(driver).doLogin(testBot1)
                .clickOnMySettings()
                .clickOnPrivacy()
                .clickOnMessagesOnlyFriendsAndSave();

        reopenDriver();

        MessageMainPage messageMainPage = new SessionMainPage(driver).doLogin(testBot2)
                .search(testBot1.getProfileId())
                .clickPeopleTab()
                .clickSearchById()
                .openUserPage(testBot1.getFullName())
                .clickWriteMessage();

        Assert.assertTrue(messageMainPage.isMessagesNotAllowed(), "Messages are allowed");
    }


}
