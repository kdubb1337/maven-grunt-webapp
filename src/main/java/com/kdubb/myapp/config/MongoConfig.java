package com.kdubb.myapp.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.kdubb.myapp.ConfigConstants;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

//@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {

	@Value(ConfigConstants.MONGO_DB_HOST)
	private String mongoDbHost;
	
	@Value(ConfigConstants.MONGO_DB_PORT)
	private int mongoDbPort;
	
	@Value(ConfigConstants.MONGO_DB_NAME)
	private String mongoDbName;
	
	@Value(ConfigConstants.MONGO_DB_USERNAME)
	private String mongoDbUsername;
	
	@Value(ConfigConstants.MONGO_DB_PASSWORD)
	private String mongoDbPassword;
	
	@Value(ConfigConstants.MONGO_DB2_HOST)
	private String mongoDb2Host;
	
	@Value(ConfigConstants.MONGO_DB2_PORT)
	private Integer mongoDb2Port;
	
	@Value(ConfigConstants.MONGO_DB2_NAME)
	private String mongoDb2Name;
	
	@Value(ConfigConstants.MONGO_DB2_USERNAME)
	private String mongoDb2Username;
	
	@Value(ConfigConstants.MONGO_DB2_PASSWORD)
	private String mongoDb2Password;
	
	@Override
	protected String getDatabaseName() {
		return mongoDbName;
	}

	@Override
	public Mongo mongo() throws Exception {
		List<ServerAddress> servers = new ArrayList<ServerAddress>();
		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		
		{
			ServerAddress server = new ServerAddress(mongoDbHost, mongoDbPort);
			servers.add(server);
			
			MongoCredential credential = MongoCredential.createMongoCRCredential(mongoDbUsername, mongoDbName, mongoDbPassword.toCharArray());
			credentials.add(credential);
		}
		
		if(StringUtils.isNotBlank(mongoDb2Host)) {
			ServerAddress server = new ServerAddress(mongoDb2Host, mongoDb2Port);
			servers.add(server);
			
			MongoCredential credential = MongoCredential.createMongoCRCredential(mongoDb2Username, mongoDb2Name, mongoDb2Password.toCharArray());
			credentials.add(credential);
		}
		
		return new MongoClient(servers, credentials);
	}
	
	@Override
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), mongoDbName);
	}
}