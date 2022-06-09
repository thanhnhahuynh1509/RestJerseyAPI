package com.huynhthanhnha.restExample.config;

import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.common.LogHelper;
import com.huynhthanhnha.restExample.helper.ResourcesHelper;

import io.swagger.jaxrs.config.BeanConfig;

public class SwaggerConfig extends HttpServlet{

	private static final long serialVersionUID = 1L;
    
	@Override
	public void init(ServletConfig config) throws ServletException {
//		LogHelper.log(this).info("Init swagger");
//		ResourcesHelper rh = new ResourcesHelper();
//		Properties prop = rh.getPropertiesFileInResources("config.properties");
//		
//		String basePath = prop.getProperty("swaggerBasePath");
//		String host = prop.getProperty("swaggerHost");
//		String title = prop.getProperty("swaggerTitle");
//		String resPackage = prop.getProperty("swaggerResourcePackage");
//		String schemes = prop.getProperty("swaggerSchemes");
//		String version = prop.getProperty("swaggerVersion");
//		
//		super.init(config);
//		BeanConfig beanConfig = new BeanConfig();
//		beanConfig.setBasePath(basePath);
//		beanConfig.setHost(host);
//		beanConfig.setTitle(title);
//		beanConfig.setResourcePackage(resPackage);
//		beanConfig.setPrettyPrint(true);
//		beanConfig.setScan(true);
//		beanConfig.setSchemes(new String[] {schemes == null || schemes.isEmpty() ? "http" : schemes});
//		beanConfig.setVersion(version == null || version.isEmpty() ? "1.0" : version);

	}
}