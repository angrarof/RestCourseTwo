package settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {
    Properties properties = new Properties();
    FileInputStream inputStream;

    public PropertiesFile(){
        loadProperties();
    }

    public void loadProperties(){
        String projectLocation = System.getProperty("user.dir");
        try{
            inputStream = new FileInputStream(projectLocation+"/src/test/java/settings/config.properties");
            properties.load(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key).toLowerCase();
    }

    public boolean getHeadless(){
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }
}
