package edu.gzhu.its;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ITSApplication extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ITSApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(ITSApplication.class, args);
    }
}

/*@SpringBootApplication
public class ITSApplication {

	public static void main(String[] args) {
		  SpringApplication springApplication =new SpringApplication(ITSApplication.class);
		springApplication.addListeners(new SystemStartUp());
		
		SpringApplication.run(ITSApplication.class, args);
	}
}*/
