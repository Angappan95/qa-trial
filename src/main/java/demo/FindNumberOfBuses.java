package demo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public class FindNumberOfBuses {

    public static void runRedbusTest() throws MalformedURLException, InterruptedException {
        //Starting Point of Execution
        System.out.println("Entering main()");

        //Launch Browser
            //System.setProperty("webdriver.chrome.logfile", "/home/kiran/Documents/Crio/qa_trial_selenium_demo/chromedriver.log");
            //System.setProperty("webdriver.chrome.verboseLogging", "true");
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxDriver driver = new FirefoxDriver();

        //Maximize and Implicit Wait for things to initailize
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Launch the URL and maximize
        driver.get("https://www.redbus.com/");

        // Wait 2 seconds for site to get loaded
        Thread.sleep(2000);

        // Accept cookie popup
        driver.findElementByXPath("//button[text()='Accept All']").click();

        // Find the element where Source city is to be entered,
        // populate the city name and select it from the drop down that comes up
        WebElement srcElement = driver.findElementById("src");
        srcElement.clear();
        srcElement.sendKeys("London");
        Thread.sleep(2000);
        WebElement srcOption = driver.findElementByXPath("//div[@class=\"autoFill autosuggest-ul rdc-src\"]//li[@class=\"liBpList\"][1]");
        srcOption.click();

        // Find the element where Destination city is to be entered,
        // populate the city name and select it from the drop down that comes up
        WebElement destElement = driver.findElementById("dest");
        destElement.clear();
        destElement.sendKeys("Birmingham");
        Thread.sleep(2000);
        WebElement destOption = driver.findElementByXPath("//div[@class=\"autoFill autosuggest-ul rdc-dest\"]//li[@data-no=1]");
        destOption.click();

        // Find the element where date needs to be entered,
        // select current date from the calendar that comes up
        WebElement calendar = driver.findElementById("onward_cal");
        Thread.sleep(1000);
        calendar.click();
        WebElement date = driver.findElementByXPath("//*[@class='DayTiles__CalendarDaysSpan-sc-14em0t0-1 fxWHuy']");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(date));
        Thread.sleep(500);
        date.click();

        // Find the locator of the search button and Click on it
        WebElement searchBtn = driver.findElementById("search_butn");
        Thread.sleep(500);
        searchBtn.click();

        // Sleep for 5 seconds. Needed in case network is slow
        Thread.sleep(3000);

        //driver.findElementByXPath("//button[text()='Ok, Got it']").click();

        // Get Number of Buses from the resultant screen and print it
        String numberOfBuses = driver.findElementByXPath("//span[text()='Buses']").getText();
        System.out.println("Number of buses is " + numberOfBuses);

        // Click on the fare column to sort it by fare
        // Get the Minimum Fare Details and print it
        driver.findElementByXPath("//a[text()='Fare']").click();
        String minFare = driver.findElementByXPath("//span[@class='f-19 f-bold']").getText();
        System.out.println(minFare + " INR is the minimum fare");

        Thread.sleep(30000);
        driver.close();
    }

    public void main(String[] args) throws InterruptedException {
        System.out.println("Entering in FindNumberOfBuses");
    }

}

