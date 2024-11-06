package utilities;

import golbals.Globals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseInfo {
private static WebDriver driver;

public static BaseInfo getBaseInfo(){
    return new BaseInfo();
}

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserType = Globals.browserType.toLowerCase();

            switch (browserType) {
                case "chrome" -> {
                    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                }
                case "firefox" -> {
                    System.out.println("Do nothing");
                }
                default -> throw new WebDriverException();
            }
        }
        return driver;
    }
}
