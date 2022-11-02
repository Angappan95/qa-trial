package demo;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class Whatsapp {

    public static void runWhatsAppTest() throws InterruptedException, MalformedURLException {
        //Starting Point of Execution
        System.out.println("Entering main()");

        // Code to Launch Chrome Browser on local setup
            // WebDriverManager.chromedriver().timeout(30).setup();
            // ChromeDriver driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxDriver driver = new FirefoxDriver();

        // Maximize and Implicit Wait for things to initailize
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //initialize contact name, number and message content
        String contactName = "Mom";
        String contactNumber = "+919444021532";
        String messageContent = "Hi! This is an automated message. By Angappan. Sent ";

        // Launching the URL and maximize
        driver.get("https://web.whatsapp.com/");

        //Waiting for Manual Scan if required
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement searchBox = driver.findElementByXPath("//div[@title='Search input textbox']");
        wait.until(ExpectedConditions.visibilityOf(searchBox));

        //Searching for the Crio Contact
        searchBox.clear();
        searchBox.sendKeys(contactNumber);

        Thread.sleep(5000);
        //Selecting the Crio Contact
        driver.findElementByXPath("(//span[@title='" + contactName + "']/parent::div)[1]").click();

        //Typing in the Message and Sending
        WebElement txtBox = driver.findElementByXPath("//div[@title='Type a message']");
        Thread.sleep(2000);
        txtBox.clear();
        new Actions(driver).
                click().
                sendKeys(txtBox, messageContent + new Timestamp(System.currentTimeMillis())).
                sendKeys(txtBox, Keys.ENTER).
                build().perform();

        // Exiting Main()
        System.out.println("Exiting main()");

        Thread.sleep(30000);
        driver.close();

    }

    public void main(String[] args) throws InterruptedException {
        System.out.println("Entering in WhatsApp Test");
    }
}