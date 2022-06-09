package com.huynhthanhnha.restExample.config;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfiguration extends ResourceConfig {
	
	public JerseyConfiguration() {
		packages("com.huynhthanhnha.restExample");
	}

}
