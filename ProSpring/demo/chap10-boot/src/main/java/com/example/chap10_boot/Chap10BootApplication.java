package com.example.chap10_boot;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.chap10_boot.service.SingerService;

@EntityScan(basePackages = {"com.example.chap10_boot.entities"})
@EnableTransactionManagement
@EnableJpaRepositories("com.example.chap10_boot.repos")
@SpringBootApplication
public class Chap10BootApplication {

	private static final Logger logger = LoggerFactory.getLogger(Chap10BootApplication.class);
	public static void main(String[] args) throws IOException {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		try (var ctx = SpringApplication.run(Chap10BootApplication.class, args)) {
			try {
			var taskExecutor = ctx.getBean("taskExecutor");
			logger.info(">>> 'taskExecutor' found: {}", taskExecutor.getClass());
			}
			catch (NoSuchBeanDefinitionException nbd) {
			logger.debug("No taskExecutor configured");
		} 
		System.in.read();
	}
}

}
