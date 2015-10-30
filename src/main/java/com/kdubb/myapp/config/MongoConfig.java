package com.kdubb.myapp.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.kdubb.myapp.config.setting.MongoSettings;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

//@Configuration
// @EnableMongoRepositories("com.kdubb.myapp.repo")
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return mongoSettings().getName();
	}

	@Bean
	@ConfigurationProperties(prefix = "mongo.db")
	public MongoSettings mongoSettings() {
		return new MongoSettings();
	}

	@Bean
	@ConfigurationProperties(prefix = "mongo.db2")
	public MongoSettings mongoSettings2() {
		return new MongoSettings();
	}

	@Override
	public Mongo mongo() throws Exception {
		List<ServerAddress> servers = new ArrayList<>();
		List<MongoCredential> credentials = new ArrayList<>();

		MongoSettings settings = mongoSettings();
		MongoSettings settings2 = mongoSettings2();

		{
			ServerAddress server = new ServerAddress(settings.getHost(), settings.getPort());
			servers.add(server);

			MongoCredential credential = MongoCredential.createMongoCRCredential(settings.getUsername(), settings.getName(),
					settings.getPassword().toCharArray());
			credentials.add(credential);
		}

		if (StringUtils.isNotBlank(settings2.getHost())) {
			ServerAddress server = new ServerAddress(settings2.getHost(), settings2.getPort());
			servers.add(server);

			MongoCredential credential = MongoCredential.createMongoCRCredential(settings2.getUsername(), settings2.getName(),
					settings2.getPassword().toCharArray());
			credentials.add(credential);
		}

		return new MongoClient(servers, credentials);
	}

	@Override
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), mongoSettings().getName());
	}
}