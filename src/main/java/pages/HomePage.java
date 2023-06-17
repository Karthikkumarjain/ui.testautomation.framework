package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;
import java.util.regex.Pattern;

public class HomePage {

    private WebDriver driver;


    private By messageContent1 = By.xpath("//div[@id='content']/p[1]");
    private By getMessageContent2 = By.xpath("//div[@id='content']/p[2]");
    private By departing = By.id("departing");
    private By returning = By.id("returning");
    private By promotionalCode = By.id("promotional_code");
    private By submitButton = By.xpath("//input[@type='submit']");
    private By redPlanetText = By.xpath("//form/h3");
    private By marsAirLogo = By.xpath("//a[normalize-space()='MarsAir']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }

    public boolean fieldsArePresent() {

        if (driver.findElement(departing).isDisplayed() && driver.findElement(returning).isDisplayed()) {
            return true;
        }
        return false;
    }

    public void addDetailsAndSearch(String monthOfDepart, String monthOfArrival) {
        ElementUtils.selectByTextForDropDown(driver.findElement(departing), monthOfDepart);
        ElementUtils.selectByTextForDropDown(driver.findElement(returning), monthOfArrival);
        driver.findElement(submitButton).click();
    }


    public String verifyMessageContent() {
        String actualString;
        String str1 = driver.findElement(messageContent1).getText();
        String str2 = driver.findElement(getMessageContent2).getText();
        if (str2.contains("Back")) {
            actualString = str1;
        } else {
            str1 = str1 + " ";
            actualString = str1.concat(str2);
        }
        return actualString;
    }

    public String verifyTextWithRedPlanet() {
        return driver.findElement(redPlanetText).getText();
    }

    public String verifyRedirectionToHomePage() {
        WebDriverWait wt = new WebDriverWait(driver, Duration.ofMillis(5000));
        wt.until(ExpectedConditions.elementToBeClickable(redPlanetText));
        driver.findElement(redPlanetText).click();
        return getHomePageTitle();

    }

    public String verifyRedirectionToHomePageOnClickingLogo() {
        WebDriverWait wt = new WebDriverWait(driver, Duration.ofMillis(5000));
        wt.until(ExpectedConditions.elementToBeClickable(marsAirLogo));
        driver.findElement(marsAirLogo).click();
        return getHomePageTitle();

    }

    public void enterPromoCodeAndClickSearch(String monthOfDepart,String monthOfArrival,String actualPromoCode){
        ElementUtils.selectByTextForDropDown(driver.findElement(departing), monthOfDepart);
        ElementUtils.selectByTextForDropDown(driver.findElement(returning), monthOfArrival);
        driver.findElement(promotionalCode).sendKeys(actualPromoCode);
        driver.findElement(submitButton).click();
    }

    public String validatePromotionalCodeAndMessageAndGetActualMessage(String actualPromoCode, String message){


        String finalMessageOnScreen = null;
        String expectedPromotionalCode = "[A-Za-z][A-Za-z][0-9]+-[A-Za-z][A-Za-z][A-Za-z]-\\d\\d\\d";
        Pattern expectedPromoCode = Pattern.compile(expectedPromotionalCode);
        if (actualPromoCode.matches(String.valueOf(expectedPromoCode))) {
            String discountDigit = String.valueOf(actualPromoCode.charAt(2));
            int totalDiscount = Integer.valueOf(discountDigit) * 10;
            String lastButOneValue = String.valueOf(actualPromoCode.charAt(8));
            String LastButTwoValue = String.valueOf(actualPromoCode.charAt(9));
            int lastValueCalcuation = (Integer.valueOf(discountDigit) + Integer.valueOf(lastButOneValue) + Integer.valueOf(LastButTwoValue)) % 10;
            System.out.println(lastValueCalcuation);
            if (String.valueOf(lastValueCalcuation).equals(String.valueOf(actualPromoCode.charAt(10)))) {
               return  finalMessageOnScreen = "Promotional code " + "[" + actualPromoCode + "]" + " used: [" + totalDiscount + "]% discount!";
                //System.out.println(finalMessageOnScreen);
            }

        }else{
            finalMessageOnScreen = "Sorry, code ["+actualPromoCode+"] is not valid";
        }

        return finalMessageOnScreen;
    }




    }

