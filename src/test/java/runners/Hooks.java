package runners;

import static utilities.Props.*;

import container.Container;
import io.cucumber.java.After;
import org.junit.Before;

public class Hooks {

  private static Container container;

  public Hooks(Container container) {
    this.container = container;
  }

  private static String environment;

  @Before
  public void setUp() {

    // Get the environment from the command line argument
    environment = System.getProperty("environment", "dev"); // Default to 'uat' if not provided

    // Determine the properties file name based on the environment
    String propertiesFile;
    switch (environment) {
      case "qa":
        propertiesFile = "qa.properties";
        break;
      case "uat":
        propertiesFile = "uat.properties";
        break;
      default:
        propertiesFile = "dev.properties";
    }

    // Load properties for the specified environment
    loadProperties(environment, propertiesFile);

    // Set test parameters based on the loaded properties
    container.baseURL = getProperty("URL");
    container.username = getProperty("Username");
    container.password = getProperty("Password");
    container.dwClientAppKey = getProperty("DW-Client-App-Key");

    container.authToken = null;
    container.appTypeID = 4;
  }

  @After
  public void tearDown() {}
}
