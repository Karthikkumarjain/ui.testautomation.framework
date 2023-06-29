import driverfactory.DriverFactory;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.PropertFileReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomJunitTests {
    private static WebDriver driver;

//    @BeforeEach
//    public void init() {
//
//
//    driver =new ChromeDriver();
//        driver.get("https://marsair.recruiting.thoughtworks.net/candidate-karthik9629846380@gmail.com");
//}


@Test
public void testDriver(){

    DriverFactory driverFactory = new DriverFactory();
    driverFactory.intitialisationOfDriver(PropertFileReader.getValue("browser"));
}

@Test
   public void LaunchBrowserAndClickOnDropDownAndSearchAndVerify(){

    WebElement element = driver.findElement(By.id("departing"));
    WebElement element1 = driver.findElement(By.id("returning"));
    WebElement element2 = driver.findElement(By.id("promotional_code"));
    WebElement element3 = driver.findElement(By.xpath("//input[@type='submit']"));
    WebElement element4 = driver.findElement(By.xpath("//div[@id='content']/h2"));
    WebElement element5 = driver.findElement(By.xpath("//div[@id='content']/p[1]"));


    selectByText(element,"July");
    selectByText(element1,"July");
           element3.click();

   assertThat(element4.getText()).isEqualTo("Search Results");
    assertThat(element5.getText()).isEqualTo("Sorry, there are no more seats available.");
                    }

        private void selectByText(WebElement element, String selectText){
        Select select = new Select(element);
            List<WebElement> departing = select.getOptions();
            Iterator<WebElement> iterator = departing.iterator();
            while(iterator.hasNext()){
                if(iterator.next().getText().equals(selectText)){
                    select.selectByVisibleText(selectText);
                }
            }

        select.selectByVisibleText(selectText);
        }


        @Test
    public void LaunchBrowserAndGetTheListOfURLS(){
        String url = "";
            driver.get("https://marsair.recruiting.thoughtworks.net/candidate-karthik9629846380@gmail.com");
            System.out.println(driver.getTitle());
           List<WebElement> links =driver.findElements(By.xpath("//a"));
           Iterator<WebElement> iterator =links.iterator();
           while (iterator.hasNext()){
               url =iterator.next().getText();
               System.out.println(url);

           }

          System.out.println(links.size());
        }


    @Test
    public void LaunchBrowserAndGetTheListOfURLSifBrokenOrNot(){
        String url2= "";

        System.out.println(driver.getTitle());
        List<WebElement> links =driver.findElements(By.xpath("//a"));
        Iterator<WebElement> iterator =links.iterator();
       while(iterator.hasNext()){

           url2 = iterator.next().getAttribute("href").toString();
           System.out.println(url2);
           verifyUrls(url2);
       }

        System.out.println(links.size());
    }

    private void verifyUrls(String linkUrl) {

        try
        {
            URL url = new URL(linkUrl);

            //Now we will be creating url connection and getting the response code
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()>=400)
            {
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage()+"is a broken link");
            }

            //Fetching and Printing the response code obtained
            else{
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
            }
        }catch (Exception e) {
        }
    }

    @Test
    public void verifyDifferentValuesinOnScreen(){
    WebElement element6 = driver.findElement(By.xpath("//div[@id = 'content']/h2"));
        WebElement element7 = driver.findElement(By.xpath("//div[@id = 'content']/form/h3"));

       assertThat(element6.getText()).isEqualTo("Welcome to MarsAir!");
        assertThat(element7.getText()).isEqualTo("Book a ticket to the red planet now!");

    }
    }


