package com.tipico.social.marketing.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.sql.DataSource;

/**
 * Created by chrism on 21/04/2016.
 */
@Configuration
@ComponentScan(basePackages = "com.tipico.social.marketing.gateway",
	excludeFilters = { @ComponentScan.Filter(Configuration.class) })
@PropertySource("classpath:account.properties")
public class MainConfig {

	@Bean(destroyMethod = "shutdown")
	public DataSource dataSource() {
		EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
		factory.setDatabaseName("spring-social-quickstart");
		factory.setDatabaseType(EmbeddedDatabaseType.HSQL);
		factory.setDatabasePopulator(databasePopulator());
		return factory.getDatabase();
	}

	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//		populator.addScript(new ClassPathResource("JdbcUsersConnectionRepository.sql",
//			JdbcUsersConnectionRepository.class));
		return populator;
	}
}
