package pages;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import settings.BasePage;

import java.util.List;

public class AmazonPage extends BasePage {
    @FindBy(id = "twotabsearchtextbox")
    private WebElement tbxSearch;

    @FindBy(xpath = "//*[@id='nav-search-submit-text']")
    private WebElement btnSearch;

    @FindBy(xpath = "(//*[@class='a-size-medium a-color-base a-text-normal'])[1]")
    private WebElement firstElement;

    @FindBy(xpath = "//*[@class='a-size-medium a-color-base a-text-normal']")
    private List<WebElement> results;

    public void searchDevice(String device){
        enterText(device, tbxSearch);
    }

    public void clickOnSearch(){
        clickOnElement(btnSearch);
    }

    public void waitForResults(){
        waitElementToAppear(firstElement);
    }

    public void clickOnLastElement(){
        int last = results.size();
        WebElement ele = driver.findElement(By.xpath("(//*[@class='a-size-medium a-color-base a-text-normal'])["+last+"]"));
        clickOnElement(ele);
    }

    public void goesToAmazon(){
        driver.get("https://www.amazon.com.mx");
    }
    public AmazonPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
}
