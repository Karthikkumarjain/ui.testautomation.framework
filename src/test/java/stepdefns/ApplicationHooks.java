package stepdefns;

import driverfactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.PropertFileReader;


public class ApplicationHooks {
    private WebDriver driver;
    private DriverFactory driverFactory;


    @Before(order = 0)
    public void launchBrowser() {
        driverFactory = new DriverFactory();
        driver = driverFactory.intitialisationOfDriver(PropertFileReader.getValue("browser"));
        driver.get(PropertFileReader.getValue("url"));

    }

    @After(order = 0)
    public void quitBrowser() {
        driver.quit();
    }


    @After(order = 1)
    public void tearDown(Scenario scenario) {
      //  if (scenario.isFailed()) {

            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);


    }

}
