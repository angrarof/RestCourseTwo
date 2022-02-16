package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.annotations.AfterTest;
import settings.DriverSetup;

public class Hooks {

    @After
    public void after(){
        DriverSetup.destroyDriver();
    }
}
