package com.huynhthanhnha.restExample.config;

import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import com.huynhthanhnha.restExample.helper.ResourcesHelper;


@Provider
public class CorsConfig implements ContainerResponseFilter {

	
    @Override
    public void filter(ContainerRequestContext request,
            ContainerResponseContext response) throws IOException {
    	
//    	ResourcesHelper rh = new ResourcesHelper();
//		Properties properties = rh.getPropertiesFileInResources("config.properties");
//    	
//		String accessControllerAllowOrigin = properties.getProperty("accessControllAllowOrigin");
		
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        response.getHeaders().add("Access-Control-Allow-Headers",
                "CSRF-Token, X-Requested-By, Authorization, Content-Type");
        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        response.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}