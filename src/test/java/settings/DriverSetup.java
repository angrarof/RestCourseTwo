package settings;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.concurrent.TimeUnit;

public class DriverSetup {
    protected static WebDriver driver = null;

    public DriverSetup(){
        initialize();
    }

    public void initialize(){
        if(driver==null){
            createDriverInstance();
        }
    }

    public void createDriverInstance(){
        String browser = new PropertiesFile().getProperty("browser");
        boolean headless = new PropertiesFile().getHeadless();
        if(browser.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            if(headless){
                options.addArguments("--headless");
            }
            driver =new ChromeDriver(options);
        }else{
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            if(headless){
                options.addArguments("--headless");
            }
            driver = new FirefoxDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WaitingTimeSetup.getWaitImplicitly(), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(WaitingTimeSetup.getWaitForPageLoad(),TimeUnit.SECONDS);
    }

    public static void destroyDriver(){
        driver.close();
        driver.quit();
        driver=null;
    }
}
