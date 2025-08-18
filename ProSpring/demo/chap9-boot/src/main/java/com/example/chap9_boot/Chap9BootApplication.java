package com.example.chap9_boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

import com.example.chap9_boot.services.AllService;

@SpringBootApplication
public class Chap9BootApplication {

	private static final Logger logger = LoggerFactory.getLogger(Chap9BootApplication.class);
	public static void main(String[] args) {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
		var ctx = SpringApplication.run(Chap9BootApplication.class, args);

		var service = ctx.getBean(AllService.class);
		logger.info(" ---- Listing singer with id = 2");
		var singer = service.findByIdWIthAlbums(2L);
		logger.info(singer.toString());
		ctx.close();


	}

}
