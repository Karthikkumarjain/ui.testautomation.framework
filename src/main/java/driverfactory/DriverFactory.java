package driverfactory;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static driverfactory.DriverFactory.getDriver;


public class DriverFactory {

    private static Logger logger = LogManager.getLogger(DriverFactory.class);

    public WebDriver driver;

    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public WebDriver intitialisationOfDriver(String browser) {
        logger.info("The browser is initialised");
        if (browser.equals("chrome")) {
            logger.info("Chrome is launched");
            threadLocal.set(new ChromeDriver());
        } else if (browser.equals("edge")) {
            threadLocal.set(new EdgeDriver());
        } else {
            logger.warn("Please enter the correct browser name");

        }

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        return getDriver();

    }

    public static synchronized WebDriver getDriver() {

        return threadLocal.get();
    }
}
