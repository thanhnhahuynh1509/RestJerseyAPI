package com.huynhthanhnha.restExample.helper;

import java.io.IOException;
import java.util.Properties;

public class ResourcesHelper {
	public Properties getPropertiesFileInResources(String fileName) {
		
		Properties prop = new Properties();
		
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(fileName));
		} catch (IOException e) {
			System.out.println("Not found: " + getClass().getClassLoader().getResourceAsStream(fileName));
			e.printStackTrace();
		}
		
		
		return prop;
	}
}
