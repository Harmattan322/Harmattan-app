package lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by DadaL1fe on 17.05.2016.
 */
public class Init {
    private static HashMap<Object, List<String>> stash;

    public static HashMap<Object, List<String>> getStash(){
        if (null == stash)
            stash = new HashMap<>();
            return stash;
    }
    public static void setStash (Object key, List<String> value) {
        getStash().put(key, value);
    }

    public static void clearStash(){
        stash.clear();
    }

    private static WebDriver driver;
    public static WebDriver getDriver() throws FileNotFoundException {
        if(null == driver) {
            createWebDriver();
        }
        return driver;
    }

    private static void createWebDriver() throws FileNotFoundException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String browser = getStash().get("st_browser").get(0);
        switch(browser){
            case"Firefox":
                capabilities.setBrowserName("firefox");
                setDriver( new FirefoxDriver(capabilities));
                break;
            case"Chrome":
                File chromeDriver = new File("src/main/resources/webdrivers/chromedriver.exe");

                System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
                capabilities.setBrowserName("chrome");
                setDriver(new ChromeDriver(capabilities));
                break;
            case"IE":
                File IEDriver = new File("src/test/java/resources/webdrivers/IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", IEDriver.getAbsolutePath());
                capabilities.setBrowserName("internet explorer");
                setDriver(new InternetExplorerDriver(capabilities));
                break;

        }
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    public static void setDriver(WebDriver driver){
        Init.driver = driver;
    }
}
