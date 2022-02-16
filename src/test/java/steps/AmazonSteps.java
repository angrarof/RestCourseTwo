package steps;

import io.cucumber.java.en.Then;
import pages.AmazonPage;
import settings.DriverSetup;

public class AmazonSteps extends DriverSetup {
    AmazonPage amazonPage = new AmazonPage(driver);

    @Then("searches something")
    public void searchesSomething() throws Throwable{
        amazonPage.searchDevice("redmi note 8");
        amazonPage.clickOnSearch();
        amazonPage.waitForResults();
        amazonPage.clickOnLastElement();
    }
}
