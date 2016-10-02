package main.resources;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class PropertiesReader {
	private static final String CONFIG_FILE_PATH = "/application.properties";
	private static final String CONFIG_PATH = System.getProperty("user.dir") + CONFIG_FILE_PATH;

	/**
	 * 
	 * @param propertyKey
	 *            it store the variable whose value needs to be fetch
	 * @return it return the value that is fetch from the key
	 */
	public static String readProperty(String propertyKey) {
		String value = null;
		Properties config = new Properties();
		try (FileInputStream inputFile = new FileInputStream(CONFIG_PATH)) {
			config.load(inputFile);
			value = config.getProperty(propertyKey);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return value;
	}
	
	public static String readFile(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("<br>");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}
}
