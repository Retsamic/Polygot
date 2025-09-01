package org.example;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties prop = new Properties();

    static{
        try (FileInputStream fis = new FileInputStream("config.properties")){
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getDbUrl(){
        String envVarName = prop.getProperty("database.url");
        return System.getenv(envVarName);
    }

    public static String getDbUser(){
        String envVarName = prop.getProperty("database.username");
        return System.getenv(envVarName);
    }

    public static String getDbPassword(){
        String envVarName = prop.getProperty("database.password");
        return System.getenv(envVarName);
    }

}
