package demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;


public class FindNumberOfBuses {

  public void main(String[] args) throws InterruptedException {
    System.out.println("Entering in FindNumberOfBuses");
  }

  public static void runRedbusTest() throws InterruptedException, MalformedURLException {
    //Starting Point of Execution
    System.out.println("Entering main() in FindNumberOfBuses");

    final DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName(BrowserType.CHROME);
    RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);

    //Launch Chrome Browser
    //    WebDriverManager.chromedriver().timeout(30).setup();
    //    ChromeDriver driver = new ChromeDriver();

    //maximize and Implicit Wait
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    //Launching the URL and maximize
    driver.get("https://www.redbus.com/");

    Thread.sleep(10000);

    //Entering Source City
    driver.findElementById("src").clear();
    driver.findElementById("src").sendKeys("Bangalore");
    driver.findElementByXPath("//*[@id='search']/div/div[1]/div/ul/li[1]").click();

    //Entering Destination City
    driver.findElementById("dest").clear();
    driver.findElementById("dest").sendKeys("Hyderabad");
    driver.findElementByXPath("//*[@id='search']/div/div[2]/div/ul/li[1]").click();

    driver.findElementByXPath("//*[@id=\"search\"]/div/div[3]/div/label").click();
    //driver.findElementByXPath("//*[@id=\"rb-calendar_onward_cal\"]/table/tbody/tr[4]/td[4]").click();
    driver.findElementByXPath("(//td[@class='current day'])[2]").click();

    Thread.sleep(300);
    //Click Search
    driver.findElementById("search_btn").click();

    //Incase network is slow
    Thread.sleep(4000);

    //Get Minimum Fare Details
    String minFareDetails = driver.findElementByClassName("fr-strts-frm").getText();
    System.out.println(minFareDetails);

    //Get Minimum Fare Details
    String numberOfBuses = driver.findElementByXPath("//span[text()='Buses']").getText();
    System.out.println(numberOfBuses);
  }

}

