package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	Properties properties;
	private static final String CONFIG_FILE_PATH = ".\\src\\test\\resources\\config.properties";

	
	public ConfigReader() {
		properties = new Properties();
		try (FileInputStream fs = new FileInputStream(CONFIG_FILE_PATH)) {
			properties.load(fs);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load config.properties file");
		}

	}
	
	//return the value in string format
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	//return the value in int format
	public int getIntProperty(String key) {
		return Integer.parseInt(properties.getProperty(key));
	}

}
