package utilities;

import java.io.IOException;
import java.util.Properties;

public class Props {

  private static Properties properties;

  public static void loadProperties(String environment, String propertiesFile) {

    properties = new Properties();
    try {
      properties.load(Props.class.getClassLoader().getResourceAsStream(propertiesFile));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String getProperty(String key) {

    //    return properties.getProperty(environment + "." + key);
    return properties.getProperty(key);
  }
}
