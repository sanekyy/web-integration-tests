package core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MessageMainPage extends HelperBase{

    private static final By MESSAGE_LIST = By.xpath(".//*[@id='msg_dialogs_list_scroller']");
    private static final By NOT_FRIEND_BLOCKED = By.xpath(".//*[@data-type='NOT_FRIEND_BLOCKED']");


    MessageMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    void check() {
        Assert.assertTrue(new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(MESSAGE_LIST)).isEnabled());
    }

    public boolean isMessagesNotAllowed(){
        return isElementPresent(NOT_FRIEND_BLOCKED);
    }
}
