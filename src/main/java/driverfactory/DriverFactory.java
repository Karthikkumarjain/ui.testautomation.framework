package driverfactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class DriverFactory {


    public WebDriver driver;

    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public WebDriver intitialisationOfDriver(String browser) {

        if (browser.equals("chrome")) {

            threadLocal.set(new ChromeDriver());
        } else if (browser.equals("edge")) {
            threadLocal.set(new EdgeDriver());
        } else {


        }

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        return getDriver();

    }

    public static synchronized WebDriver getDriver() {

        return threadLocal.get();
    }



}
