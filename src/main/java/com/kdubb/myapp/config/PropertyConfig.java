package com.kdubb.myapp.config;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertyConfig {

	private static final String ENVIRONMENT_SYS_PROPERTY = AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;
	private static final Logger LOG = LogManager.getLogger(PropertyConfig.class);

	public static String getEnvironment() {
		String env = System.getProperty(ENVIRONMENT_SYS_PROPERTY);

		if (StringUtils.isBlank(env))
			env = "LOCAL";

		return env;
	}

	@Bean
	public static PropertyPlaceholderConfigurer properties() {
		String env = getEnvironment();
		LOG.info("Starting with environment set to - [" + env + "]");

		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[] {
				new ClassPathResource("myapp.properties"),
				new ClassPathResource("myapp-" + env + ".properties") };

		ppc.setLocations(resources);
		ppc.setIgnoreUnresolvablePlaceholders(true);
		return ppc;
	}
}