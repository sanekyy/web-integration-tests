package core.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SettingsMainPage extends HelperBase {

    private static final By COMPACT_PROFILE = By.xpath(".//*[@class='compact-profile']");

    private static final By PRIVATE_BTN = By.xpath(".//*[contains(@class, 'ic_nav_private')]");
    private static final By PERSONAL_MESSAGES_ONLY_FRIEND_CHECKBOX = By.xpath(".//*[@name='st.accessForPERSONAL_MESSAGES' and @value='1']");
    private static final By SAVE_BTN = By.xpath(".//*[@name='button_changeProfileType']");


    SettingsMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    void check() {
        Assert.assertTrue(new WebDriverWait(driver, 10).until(d -> isElementPresent(COMPACT_PROFILE)));
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(COMPACT_PROFILE));
    }

    public SettingsMainPage clickOnPrivacy(){
        click(PRIVATE_BTN);
        return this;
    }

    public SettingsMainPage clickOnMessagesOnlyFriendsAndSave() throws InterruptedException {
        if(!isElementSelected(PERSONAL_MESSAGES_ONLY_FRIEND_CHECKBOX)) {
            scrollWithOffset(PERSONAL_MESSAGES_ONLY_FRIEND_CHECKBOX, 0, -50);
            Thread.sleep(1000);
            click(PERSONAL_MESSAGES_ONLY_FRIEND_CHECKBOX);
            click(SAVE_BTN);
        }
        return this;
    }
}
