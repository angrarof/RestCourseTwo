package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import settings.BasePage;

public class MapsPage extends BasePage {
    @FindBy(css = "#action-menu ul li:nth-of-type(1)")
    private WebElement placeCoordinates;

    @FindBy(css = "#scene")
    private WebElement mapsScene;

    @FindBy(id = "searchboxinput")
    private WebElement searchBox;

    @FindBy(css = "#pane .x3AX1-LfntMc-header-title")
    private WebElement resultPane;

    public void goToMaps(){
        driver.get("https://www.google.com/maps");
    }

    public String getCoordinatesOfPlace(){
        waitElementToAppear(resultPane);
        getContextMenu(mapsScene);
        return getElementText(placeCoordinates);
    }

    public void enterTextOnSearchBox(String place){
        enterText(place, searchBox);
        searchBox.sendKeys(Keys.ENTER);
    }

    public MapsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
