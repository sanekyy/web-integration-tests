package core.pages;

import core.Transformer;
import core.wrapers.CommentWrapper;
import model.TestBot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class PhotoMainPage extends HelperBase {

    private static final By PHOTO = By.xpath(".//*[@id='photo-layer_img_w']");
    private static final By PHOTO_LAYER_INFO = By.xpath(".//*[@class='photo-layer_info']");
    private static final By ADD_COMMENT_BOX = By.xpath(".//*[@class='comments_add']");
    private static final By COMMENT_INPUT = By.xpath(".//div[contains(@class,'js-comments_add')]");
    private static final By COMMENT_SUBMIT_BTN = By.xpath(".//*[@data-l='t,submit']");
    private static final By COMMENT = By.xpath(".//*[@class='comments_lst_cnt']//*[@class='comments_i']");
    private static final By COMMENTS_LIST = By.xpath(".//*[@class='comments_lst_cnt']");

    PhotoMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    void check() {
        Assert.assertTrue(new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(PHOTO)).isEnabled());
        Assert.assertTrue(new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(PHOTO_LAYER_INFO)).isEnabled());
    }

    public PhotoMainPage typeComment(String comment) throws InterruptedException {
        new Actions(driver)
                .sendKeys(driver.findElement(COMMENTS_LIST), Keys.PAGE_DOWN)
                .build()
                .perform();

        type(comment, COMMENT_INPUT);
        return new PhotoMainPage(driver);
    }

    public PhotoMainPage submitComment() {
        sendKeys(COMMENT_INPUT, Keys.ENTER);
        return new PhotoMainPage(driver);
    }

    public boolean isCommentPresent(String comment, TestBot testBot) {
        for (CommentWrapper commentWrapper : getComments()) {
            if (commentWrapper.getComment().equals(comment)
                    && commentWrapper.getOwnerFullName().equals(testBot.getFullName())) {
                return true;
            }
        }

        return false;
    }

    private List<CommentWrapper> getComments() {
        List<WebElement> comments = driver.findElements(COMMENT);
        return Transformer.wrapComments(comments, driver);
    }
}
