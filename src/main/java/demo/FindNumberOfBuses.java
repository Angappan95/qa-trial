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

  }

  public static void runRedbusTest() throws InterruptedException, MalformedURLException {
    //Starting Point of Execution
    System.out.println("Entering FindNumberOfBuses");

    //Launch Chrome Browser using Zalenium
    final DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName(BrowserType.CHROME);
    RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);

    //Maximize and Implicit Wait for things to initailize
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    //Launch the URL and maximize
    driver.get("https://www.redbus.com/");

    // Wait 5 seconds for site to get loaded
    Thread.sleep(5000);

    // Find the element where Source city is to be entered, 
    // populate the city name and select it from the drop down that comes up
    driver.findElementById("src").clear();
    driver.findElementById("src").sendKeys("London");
    driver.findElementByXPath("//*[@id='search']/div/div[1]/div/ul/li[1]/i").click();

    // Find the element where Destination city is to be entered, 
    // populate the city name and select it from the drop down that comes up
    driver.findElementById("dest").clear();
    driver.findElementById("dest").sendKeys("Birmingham");
    driver.findElementByXPath("//*[@id='search']/div/div[2]/div/ul/li[1]").click();

    // Find the element where date needs to be entered,
    // select current date from the calendar that comes up 
    driver.findElementByXPath("//*[@id=\"search\"]/div/div[3]/div/label").click();
    driver.findElementByXPath("(//td[@class='current day'])[2]").click();

    Thread.sleep(300);
    // Find the locator of the search button and Click on it
    driver.findElementById("search_btn").click();

    // Sleep for 4 seconds. Needed in case network is slow
    Thread.sleep(4000);

    // Get Number of Buses from the resultant screen and print it
    String numberOfBuses = driver.findElementByXPath("//span[text()='Buses']").getText();
    System.out.println("Number of buses is "  + numberOfBuses);

    // Click on the fare column to sort it by fare
    // Get the Minimum Fare Details and print it
    driver.findElementByXPath("//a[text()='Fare']").click();
    String minFare = driver.findElementByXPath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[6]/a").getText();
    System.out.println(minFare + " USD is the minimum fare");

  }
}
