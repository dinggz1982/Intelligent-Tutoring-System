package edu.gzhu.its;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.gzhu.its.base.startup.SystemStartUp;

@SpringBootApplication
public class ITSApplication {

	public static void main(String[] args) {
		  SpringApplication springApplication =new SpringApplication(ITSApplication.class);
		springApplication.addListeners(new SystemStartUp());
		
		SpringApplication.run(ITSApplication.class, args);
	}
}
