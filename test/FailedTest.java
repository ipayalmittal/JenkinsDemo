import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.TestCase;

public class FailedTest extends TestCase {

  public static void testOne() {
    String apiKey = null;
    String apiSecret = null;

    try {
         FileInputStream fstream = new FileInputStream(System.getProperty("user.home") + "/.testingbot");
         // Get the object of DataInputStream
         DataInputStream in = new DataInputStream(fstream);
         BufferedReader br = new BufferedReader(new InputStreamReader(in));
         String strLine = br.readLine();
         String[] data = strLine.split(":");
         apiKey = data[0];
         apiSecret = data[1];
       } catch (Exception e) { return; }

      if ((apiKey == null) || (apiSecret == null)) {
          return;
      }

    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability("browserName", "IE");
    caps.setCapability("version", "9");
    caps.setCapability("platform", "WINDOWS");

    try {
      WebDriver driver = new RemoteWebDriver(new URL("http://" + apiKey + ":" + apiSecret + "@hub.testingbot.com/wd/hub"), caps);
      driver.get("https://testingbot.com");
      System.out.println("TestingBotSessionID=" + ((RemoteWebDriver) driver).getSessionId().toString());
      assertEquals(false, false);
      driver.quit();
    }
    catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
  }
}
