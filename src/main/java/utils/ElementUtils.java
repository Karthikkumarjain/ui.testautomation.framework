package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class ElementUtils {

    public static void selectByTextForDropDown(WebElement element, String textToSelect){

        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        Iterator<WebElement> iterator = options.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getText().equals(textToSelect)){
                select.selectByVisibleText(textToSelect);
            }
        }
    }


}
