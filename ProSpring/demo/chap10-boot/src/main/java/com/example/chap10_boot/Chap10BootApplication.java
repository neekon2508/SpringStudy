package com.example.chap10_boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	public static void main(String[] args) {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		var ctx = SpringApplication.run(Chap10BootApplication.class, args);
		var service = ctx.getBean(SingerService.class);

		logger.info(" ---- Listing singers:");
		service.findAll().forEach(s-> logger.info(s.toString()));
	}

}
