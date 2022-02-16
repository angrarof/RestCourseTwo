package settings;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

abstract public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, WaitingTimeSetup.getWaitForElement());
        actions = new Actions(this.driver);
    }

    public void waitElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementToAppear(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void getContextMenu(WebElement element){
        actions.contextClick(element).perform();
    }

    public void clickOnElement(WebElement element){
        waitElementToBeClickable(element);
        element.click();
    }

    public void enterText(String text, WebElement element){
        waitElementToAppear(element);
        element.sendKeys(text);
    }

    public String getElementText(WebElement element){
        waitElementToAppear(element);
        return element.getText().toLowerCase().trim();
    }
}
