package com.example.chap4_boot;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chap4BootApplication {

	private static Logger logger = LoggerFactory.getLogger(Chap4BootApplication.class);
	public static void main(String[] args) {
		var ctx = SpringApplication.run(Chap4BootApplication.class, args);
		assert (ctx != null);
		Arrays.stream(ctx.getBeanDefinitionNames()).forEach(logger::info);
	}

}
