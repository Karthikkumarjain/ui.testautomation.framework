package driverfactory;


import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;


public class DriverFactory {


    public WebDriver driver;

    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public WebDriver intitialisationOfDriver(String browser) {

        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            threadLocal.set(new ChromeDriver(options));
//            try {
//                threadLocal.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
//            }catch (Exception e){
//                e.printStackTrace();
//            }
        } else if (browser.equals("edge")) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            threadLocal.set(new EdgeDriver(options));
        }

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        return getDriver();

    }

    public static synchronized WebDriver getDriver() {

        return threadLocal.get();
    }


}
