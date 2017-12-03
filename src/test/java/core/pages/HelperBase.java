package core.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

abstract class HelperBase {
    WebDriver driver;
    private boolean acceptNextAlert = true;

    HelperBase(WebDriver driver) {
        this.driver = driver;
        check();
    }

    abstract void check();

    void type(String text, By by) {
        Assert.assertTrue(new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(by)).isEnabled());

        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }

    void click(By by) {
        scrollWithOffset(by, 0, -100);
        Assert.assertTrue(new WebDriverWait(driver, 10).until(d -> isElementVisible(by)));
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(by));
        Assert.assertTrue(new WebDriverWait(driver, 3).until(d -> {
            try {
                driver.findElement(by).click();
                return true;
            } catch (Exception e) {
                return false;
            }
        }));
    }

    void sendKeys(By by, Keys key) {
        driver.findElement(by).sendKeys(key);
    }

    boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    boolean isElementVisible(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    boolean isElementSelected(By by){
        return driver.findElement(by).isSelected();
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    void scrollWithOffset(By by, int x, int y) {
        WebElement element = driver.findElement(by);

        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";

        ((JavascriptExecutor) driver).executeScript(code, element, x, y);

    }
}
