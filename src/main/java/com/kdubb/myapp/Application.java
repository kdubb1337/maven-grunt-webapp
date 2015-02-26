package com.kdubb.myapp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.AbstractEnvironment;

@EnableAutoConfiguration(exclude={ThymeleafAutoConfiguration.class, 
		DataSourceAutoConfiguration.class})
@ComponentScan
public class Application extends SpringBootServletInitializer {
	
	private static final Logger LOG = LogManager.getLogger(Application.class);
	
	public static void main(String[] args) {
		init();
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		init();
		return application.sources(Application.class);
	}
	
	private static void init() {
		String defaultEnv = System.getProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "LOCAL");
		System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, defaultEnv);
		
		String env = System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, defaultEnv);
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, env);
		
		LOG.warn("Starting with environment -> {" + env + "}");
		System.setProperty("logging.config", "classpath:logback.xml");
	}
}
